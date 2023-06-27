package com.work.warehouse.service;


import com.work.warehouse.config.ResultReturn;
import com.work.warehouse.entity.Message;

public interface MessageService {
    ResultReturn addMessage(Message message);
    ResultReturn findMessageById(Integer id);

    ResultReturn updateMessageStatus(Integer id);

    ResultReturn findMessageTimeSlot(String beforeTime, String afterTime, Integer pageSize, Integer count);
}
