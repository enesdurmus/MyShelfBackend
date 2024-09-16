package com.enesd.myshelfbackend.model.listeners;

import com.enesd.myshelfbackend.messaging.MessageProducer;
import com.enesd.myshelfbackend.model.entities.User;
import com.github.javafaker.Faker;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserEntityListener {
    private final MessageProducer messageProducer;

    @PrePersist
    private void beforePersist(User user) {
        Faker faker = new Faker();
        user.setDisplayName("user_" + faker.number().digits(12));
        messageProducer.sendDisplayNameSyncMessage();
    }
}
