package com.work.warehouse.corn;

import com.work.warehouse.config.AmqpConfig;
import com.work.warehouse.entity.Message;
import com.work.warehouse.mapper.MessageMapper;
import lombok.extern.log4j.Log4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 死信消费者
 */
@Component
@Log4j
public class DlxMessageConsumer {
    @Autowired
    private MessageMapper messageMapper;

    @RabbitListener(queues = AmqpConfig.DLX_QUEUE_NAME)
    private void sendMessage(Message message){
        Message messageById = messageMapper.findMessageById(message.getId());
        // 查询消息的状态
        if (messageById.getMessageStatus() == 0) {
            // 消息未必读取，发送短信给admin用户
            log.info("发送短信");
        } else {
            // 消息已被读取，不发送短信给admin用户，该死信不做处理
            log.info("admin已查看");
        }
        log.info("开始处理");
    }
}
