package com.enesd.myshelfbackend.dto;

import com.enesd.myshelfbackend.enums.SubscriptionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionDTO {
    private Long id;
    private String name;
    private SubscriptionType subscriptionType;
    private Float durationHours;
    private Integer amount;
    private BigDecimal price;
}
