package com.work.warehouse.controller;

import com.work.warehouse.config.ResultReturn;
import com.work.warehouse.entity.Message;
import com.work.warehouse.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @RequestMapping("/addMessage")
    public ResultReturn addMessage(@RequestBody Message message){
        return messageService.addMessage(message);
    }

    @RequestMapping("/findMessageById/{id}")
    public ResultReturn findMessageById(@PathVariable("id") Integer id){
        return messageService.findMessageById(id);
    }

    @RequestMapping("/findMessageTimeSlot/{beforeTime}/{afterTime}/{pageSize}/{count}")
    public ResultReturn findMessageTimeSlot(@PathVariable(value = "beforeTime", required = false) String beforeTime,
                                            @PathVariable(value = "afterTime", required = false) String afterTime,
                                            @PathVariable("pageSize") Integer pageSize,
                                            @PathVariable("count") Integer count){
        return messageService.findMessageTimeSlot(beforeTime, afterTime, pageSize, count);
    }

    @RequestMapping("/updateMessageStatus/{id}")
    public ResultReturn updateMessageStatus(@PathVariable("id") Integer id){
        return messageService.updateMessageStatus(id);
    }
}
