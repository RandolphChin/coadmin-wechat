package com.gitee.coadmin.modules.mq.callback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
@Slf4j
public class MsgSendReturnCallback implements RabbitTemplate.ReturnCallback {

    /**
     * 当消息从交换机到队列失败时，该方法被调用。（若成功，则不调用）
     * 需要注意的是：该方法调用后，{@link MsgSendConfirmCallBack}中的confirm方法也会被调用，且ack = true
     * @param message
     * @param replyCode
     * @param replyText
     * @param exchange
     * @param routingKey
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info("MsgSendReturnCallback [消息从交换机到队列失败]  message：{}",message);

        // TODO 消息从交换机到队列失败，重新发送

    }
}
