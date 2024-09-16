package com.enesd.myshelfbackend.config;

import com.enesd.myshelfbackend.consts.RabbitMQQueueNames;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue firstQueue() {
        return new Queue(RabbitMQQueueNames.DISPLAY_NAME_SYNC_QUEUE, false);
    }
}
