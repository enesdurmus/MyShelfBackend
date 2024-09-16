package com.enesd.myshelfbackend.messaging;

import com.enesd.myshelfbackend.consts.RabbitMQQueueNames;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {

    @RabbitListener(queues = RabbitMQQueueNames.DISPLAY_NAME_SYNC_QUEUE)
    public void receiveDisplayNameSyncMessage(String message) {
        System.out.println("Received from firstQueue: " + message);
    }
}