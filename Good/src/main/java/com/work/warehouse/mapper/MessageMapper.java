package com.work.warehouse.mapper;

import com.work.warehouse.entity.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface MessageMapper {
    int addMessage(Message message);
    Message findMessageById(Integer id);
    int updateMessageStatus(Integer id);
    List<Message> findMessageTimeSlot(Date beforeDate, Date afterDate, Integer pageSize, Integer count);
}
