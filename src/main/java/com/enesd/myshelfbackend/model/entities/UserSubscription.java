package com.enesd.myshelfbackend.model.entities;

import com.enesd.myshelfbackend.model.abstracts.Auditable;
import com.enesd.myshelfbackend.model.compositeKeys.UserSubscriptionId;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "user_subscriptions")
@IdClass(UserSubscriptionId.class)
@Data
@RequiredArgsConstructor
public class UserSubscription extends Auditable {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;

    @Column(name = "ends_at", nullable = false)
    private Instant endsAt;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;
}