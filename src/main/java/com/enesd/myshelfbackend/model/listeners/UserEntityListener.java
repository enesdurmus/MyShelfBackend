package com.enesd.myshelfbackend.model.listeners;

import com.enesd.myshelfbackend.model.entities.User;
import com.github.javafaker.Faker;
import jakarta.persistence.PrePersist;
import org.springframework.stereotype.Component;

@Component
public class UserEntityListener {
    @PrePersist
    private void beforePersist(User user) {
        Faker faker = new Faker();
        user.setDisplayName("user_" + faker.number().digits(12));
        System.out.println("1");
    }
}
