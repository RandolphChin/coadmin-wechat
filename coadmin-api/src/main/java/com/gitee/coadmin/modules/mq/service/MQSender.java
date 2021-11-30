package com.gitee.coadmin.modules.mq.service;

import cn.hutool.core.util.IdUtil;
import com.gitee.coadmin.modules.mq.config.MQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MQSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    // 接收设备报文后响应客户端
    public void deviceResponse(String msg){
        String msgId = IdUtil.getSnowflake().nextIdStr();

        Message message = MessageBuilder.withBody(msg.getBytes())
        .setContentType(MessageProperties.CONTENT_TYPE_JSON).setCorrelationId(msgId)
                .build();
        /*将 msgId和 CorrelationData绑定*/
        CorrelationData correlationData = new CorrelationData(msgId);
        rabbitTemplate.convertAndSend(MQConfig.DEFAULT_EXCHANGE,MQConfig.DEVICE_LISTEN_ROUTING_KEY,message,correlationData);
    }

    // 发送命令
    public void sendOrder(String msg){
        String msgId = IdUtil.getSnowflake().nextIdStr();

        Message message = MessageBuilder.withBody(msg.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON).setCorrelationId(msgId)
                .build();
        /*将 msgId和 CorrelationData绑定*/
        CorrelationData correlationData = new CorrelationData(msgId);
        rabbitTemplate.convertAndSend(MQConfig.DEFAULT_EXCHANGE,MQConfig.DEVICE_ORDER_ROUTING_KEY,message,correlationData);
    }
}
