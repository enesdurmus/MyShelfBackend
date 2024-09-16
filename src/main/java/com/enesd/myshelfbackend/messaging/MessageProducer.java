package com.enesd.myshelfbackend.messaging;

import com.enesd.myshelfbackend.consts.RabbitMQQueueNames;
import com.enesd.myshelfbackend.model.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageProducer {
    private final RabbitTemplate rabbitTemplate;

    public void sendDisplayNameSyncMessage(User user) {
        rabbitTemplate.convertAndSend(RabbitMQQueueNames.DISPLAY_NAME_SYNC_QUEUE, user);
    }
}
