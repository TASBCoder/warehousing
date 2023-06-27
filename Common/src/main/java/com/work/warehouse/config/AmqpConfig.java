package com.work.warehouse.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AmqpConfig {

    public static final String NORMAL_QUEUE_NAME = "normal_queue_name";
    public static final String NORMAL_EXCHANGE_NAME = "normal_exchange_name";
    public static final String NORMAL_ROUTING_KEY = "normal_routing_key";
    public static final String DLX_QUEUE_NAME = "dlx_queue_name";
    public static final String DLX_EXCHANGE_NAME = "dlx_exchange_name";
    public static final String DLX_ROUTING_KEY = "dlx_routing_key";

    /**
     * 死信队列
     * @return
     */
    @Bean
    Queue dlxQueue() {
        return new Queue(DLX_QUEUE_NAME, true);
    }

    /**
     * 死信交换机
     * @return
     */
    @Bean
    DirectExchange dlxExchange() {
        return new DirectExchange(DLX_EXCHANGE_NAME);
    }

    /**
     * 绑定死信队列和死信交换机
     * @return
     */
    @Bean
    Binding dlxBinding() {
        return BindingBuilder.bind(dlxQueue()).to(dlxExchange())
                .with(DLX_ROUTING_KEY);
    }

    /**
     * 普通消息队列
     * @return
     */
    @Bean
    Queue normalQueue() {
        Map<String, Object> args = new HashMap<>();
        //设置消息过期时间，此方法是在队列的颗粒度设置，比较局限，所以在消息上设置过期时间
//        args.put("x-message-ttl", 1000*5);
        //设置死信交换机
        args.put("x-dead-letter-exchange", DLX_EXCHANGE_NAME);
        //设置死信 routing_key
        args.put("x-dead-letter-routing-key", DLX_ROUTING_KEY);
        return new Queue(NORMAL_QUEUE_NAME, true, false, false, args);
    }

    /**
     * 普通交换机
     * @return
     */
    @Bean
    DirectExchange normalExchange() {
        return new DirectExchange(NORMAL_EXCHANGE_NAME);
    }

    /**
     * 绑定普通队列和与之对应的交换机
     * @return
     */
    @Bean
    Binding nomalBinding() {
        return BindingBuilder.bind(normalQueue())
                .to(normalExchange())
                .with(NORMAL_ROUTING_KEY);
    }
}
