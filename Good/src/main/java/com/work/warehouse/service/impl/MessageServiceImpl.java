package com.work.warehouse.service.impl;

import com.work.warehouse.config.ResultReturn;
import com.work.warehouse.entity.Message;
import com.work.warehouse.mapper.MessageMapper;
import com.work.warehouse.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;

    public ResultReturn addMessage(Message message){
        int i = messageMapper.addMessage(message);
        if (i>0){
            return ResultReturn.success();
        }
        return ResultReturn.fail("消息存入数据库失败");
    }

    public ResultReturn findMessageById(Integer id){
        Message messageById = messageMapper.findMessageById(id);
        return ResultReturn.success(messageById);
    }

    public ResultReturn updateMessageStatus(Integer id){
        int i = messageMapper.updateMessageStatus(id);
        if (i>0){
            return ResultReturn.success();
        }
        return ResultReturn.fail("消息状态修改失败");
    }

    @Override
    public ResultReturn findMessageTimeSlot(String beforeTime, String afterTime, Integer pageSize, Integer count) {
        Date beforeDate = null;
        Date afterDate = null;
        try{
            if (beforeTime != null && afterTime != null) {
                beforeDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(beforeTime);
                afterDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(afterTime);
                if (afterDate.before(beforeDate)) {
                    return ResultReturn.fail("截至日期不能在开始日期之前");
                }
            }
            List<Message> messageTimeSlot = messageMapper.findMessageTimeSlot(beforeDate, afterDate, pageSize, count);
            return ResultReturn.success(messageTimeSlot);
        } catch (Exception e) {
            log.error("字符串转日期异常",e);
            return ResultReturn.fail("查询错误");
        }
    }
}
