package com.work.warehouse.service.impl;

import com.work.warehouse.config.AmqpConfig;
import com.work.warehouse.config.ResultReturn;
import com.work.warehouse.constant.MessageConstant;
import com.work.warehouse.entity.Good;
import com.work.warehouse.entity.Ledger;
import com.work.warehouse.entity.Message;
import com.work.warehouse.mapper.GoodMapper;
import com.work.warehouse.mapper.LedgerMapper;
import com.work.warehouse.service.LedgerService;
import com.work.warehouse.service.MessageService;
import lombok.extern.log4j.Log4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@Log4j
public class LedgerServiceImpl implements LedgerService {

    @Autowired
    private LedgerMapper ledgerMapper;
    @Autowired
    private MessageService messageService;
    @Autowired
    private GoodMapper goodMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Value("${good.server.address}")
    private String goodHost;

    @Value("${good.server.port}")
    private String goodPost;

    private Good findGood(String goodIdentify){
        return goodMapper.selectGoodByIdentify(goodIdentify);
    }

    /**
     * 入库
     * @param goodIdentify 商品唯一标识
     * @param name 商品名称
     * @param price 商品价格
     * @param number 入库数目
     * @param description 商品描述
     * @param store 门店信息
     * @param operatorId 入库操作人id
     * @return
     */
    public ResultReturn InWarehousing(String goodIdentify,
                                      String name,
                                      Float price,
                                      Integer number,
                                      String description,
                                      String store,
                                      String operatorId) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        Ledger ledger = new Ledger();
        ledger.setGoodIdentify(goodIdentify);
        ledger.setSerialNumber(uuid);
        ledger.setNumber(number);
        ledger.setStore(store);
        ledger.setOperatorId(operatorId);
        // 0 代表入库，1 代表出库
        ledger.setOperator(0);
        int i = ledgerMapper.InWarehousing(ledger);
        if (i > 0) {
            // 查看仓库中是否有该商品
            Good good = findGood(goodIdentify);
            if (good != null) {
                // 有该商品，增加商品的数目
                Integer stock = good.getStock();
                good.setStock(stock + number);
                goodMapper.updateGoodByIdentify(good);
            } else {
                // 没有该商品，新增该商品的管理
                good.setIdentify(goodIdentify);
                good.setName(name);
                good.setPrice(price);
                good.setStock(number);
                good.setDescription(description);
                goodMapper.addGood(good);
            }
            // 插入成功
            return ResultReturn.success();
        }
        // 插入失败
        return ResultReturn.fail("失败");
    }

    /**
     * 出库
     */
    public synchronized ResultReturn OutWarehousing(String goodIdentify, Integer number, String store, String operatorId) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        Ledger ledger = new Ledger();
        ledger.setGoodIdentify(goodIdentify);
        ledger.setSerialNumber(uuid);
        ledger.setNumber(number);
        ledger.setStore(store);
        ledger.setOperatorId(operatorId);
        // 0 代表入库，1 代表出库
        ledger.setOperator(1);
        // 根据商品的唯一标识查询出当前商品的库存数
        Good good = goodMapper.selectGoodByIdentify(goodIdentify);
        Integer stock = good.getStock();
        if(number>stock){
            return ResultReturn.fail("该商品库存不足，请及时补充库存");
        }

        //3.处理逻辑,有库存
        try {
            //4.添加台账记录
            int insert = ledgerMapper.InWarehousing(ledger);
            if(insert<0){
                return ResultReturn.fail("台账添加失败(出库)");
            }
            //5.减库存
            good.setStock(stock-number);
            int i = goodMapper.updateGoodByIdentify(good);
            if (i > 0) {
                return ResultReturn.success();
            }
            log.info("该商品出库成功，商品编号：");
            //5.封装消息
            Message message = new Message();
            message.setOutTime(new Date(System.currentTimeMillis()));
            message.setContent(message.getOutTime()+"商品："+ goodIdentify + "已出库：" + number + "个，请及时查收验证，账单流水号：" + uuid);
            //6.发送消息给admin用户
            sendMessage(message);
            //7.保存消息到消息表
            ResultReturn resultReturn = messageService.addMessage(message);
            if (resultReturn.getCode() == 200) {
                //8.保存消息到redis
                String key = MessageConstant.REDIS_MESSAGE_PREFIX + message.getId();
                redisTemplate.opsForValue().set(key, message.getContent(), 3, TimeUnit.HOURS);
                //9. 将消息发送到队列中，并设置其延迟时间
                rabbitTemplate.convertAndSend(AmqpConfig.NORMAL_EXCHANGE_NAME, AmqpConfig.NORMAL_ROUTING_KEY, message, msg -> {
                    msg.getMessageProperties().setExpiration("10800000");
                    return msg;
                });
            }
        }catch (Exception e){
            log.error("商品出库错误", e);
        }
        return ResultReturn.success();
    }

    private void sendMessage(Message message){
        log.info(message.getContent()+"发送给admin用户");
    }
}
