package com.enesd.myshelfbackend.model.entities;

import com.enesd.myshelfbackend.enums.SubscriptionType;
import com.enesd.myshelfbackend.model.abstracts.Auditable;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "subscriptions")
@Data
public class Subscription extends Auditable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "subscription_type", nullable = false)
    private SubscriptionType subscriptionType;

    @Column(name = "duration_hours", nullable = false)
    private Float durationHours;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "price", nullable = false)
    private BigDecimal price;
}