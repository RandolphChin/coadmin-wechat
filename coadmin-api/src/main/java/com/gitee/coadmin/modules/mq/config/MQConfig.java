package com.gitee.coadmin.modules.mq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MQConfig {
    public static final String DEVICE_LISTEN_QUEUE = "device_listen"; // 设备端接收信息队列
    public static final String DEVICE_CREATE_QUEUE = "device_create";
    public static final String DEVICE_STATUS_QUEUE = "device_status"; // 设备状态上报队列
    public static final String DEVICE_ORDER_QUEUE = "device_order"; // 下发命令
    public static final String DEFAULT_EXCHANGE = "amq.topic";
    public static final String DEVICE_CREATE_ROUTING_KEY = "device_create"; // 设备创建主题   device_create
    public static final String DEVICE_LISTEN_ROUTING_KEY = "device_listen"; // 设备接收主题
    public static final String DEVICE_STATUS_ROUTING_KEY = "device_status"; // 设备上报主题   device_status
    public static final String DEVICE_ORDER_ROUTING_KEY = "device_order";

    @Bean
    public TopicExchange defaultExchange(){
        return new TopicExchange(DEFAULT_EXCHANGE,true,false);
    }

    @Bean
    public Queue deviceCreateQueue() {
        return new Queue(DEVICE_CREATE_QUEUE,true,false,false);
    }


    @Bean
    public Binding deviceCreateBind(){
        return BindingBuilder.bind(deviceCreateQueue()).to(defaultExchange()).with(DEVICE_CREATE_ROUTING_KEY);
    }

    @Bean
    public Queue deviceListenQueue(){
        return new Queue(DEVICE_LISTEN_QUEUE,true,false,false);
    }

    @Bean
    public Binding deviceListenBind(){
        return BindingBuilder.bind(deviceListenQueue()).to(defaultExchange()).with(DEVICE_LISTEN_ROUTING_KEY);
    }

    @Bean
    public Queue deviceStatusQueue(){
        return new Queue(DEVICE_STATUS_QUEUE,true,false,false);
    }

    @Bean
    public Binding deviceStatusBind(){
        return BindingBuilder.bind(deviceStatusQueue()).to(defaultExchange()).with(DEVICE_STATUS_ROUTING_KEY);
    }

    @Bean
    public Queue deviceOrderQueue(){
        return new Queue(DEVICE_ORDER_QUEUE,true,false,false);
    }

    @Bean
    public Binding deviceOrderBind(){
        return BindingBuilder.bind(deviceOrderQueue()).to(defaultExchange()).with(DEVICE_ORDER_ROUTING_KEY);
    }
}
