package com.enesd.myshelfbackend.config;

import com.enesd.myshelfbackend.consts.RabbitMQQueueNames;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue displayNameSyncQueue() {
        return new Queue(RabbitMQQueueNames.DISPLAY_NAME_SYNC_QUEUE, false);
    }

    @Bean
    public SimpleMessageConverter converter() {
        SimpleMessageConverter converter = new SimpleMessageConverter();
        converter.setAllowedListPatterns(List.of("com.enesd.myshelfbackend.*", "java.util.*", "java.lang.*", "org.hibernate.*"));
        return converter;
    }
}
