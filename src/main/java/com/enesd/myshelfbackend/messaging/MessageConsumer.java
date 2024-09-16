package com.enesd.myshelfbackend.messaging;

import com.enesd.myshelfbackend.consts.RabbitMQQueueNames;
import com.enesd.myshelfbackend.model.entities.User;
import com.enesd.myshelfbackend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageConsumer {
    private final UserService userService;

    @RabbitListener(queues = RabbitMQQueueNames.DISPLAY_NAME_SYNC_QUEUE)
    public void receiveDisplayNameSyncMessage(User user) {
        userService.syncDisplayNameWithElastic(user);
    }
}