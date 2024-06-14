package com.enesd.myshelfbackend.model.request;

import com.enesd.myshelfbackend.enums.SubscriptionType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateSubscriptionRequest {

    @NotEmpty
    private String name;

    @NotEmpty
    private SubscriptionType subscriptionType;

    @NotNull
    private Float durationHours;

    @NotNull
    private Integer amount;

    @NotNull
    private BigDecimal price;
}
