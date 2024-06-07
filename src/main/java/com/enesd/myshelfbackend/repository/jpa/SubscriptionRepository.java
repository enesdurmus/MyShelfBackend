package com.enesd.myshelfbackend.repository.jpa;

import com.enesd.myshelfbackend.model.entities.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
