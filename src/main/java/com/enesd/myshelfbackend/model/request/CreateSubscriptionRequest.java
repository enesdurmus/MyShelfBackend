package com.enesd.myshelfbackend.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateSubscriptionRequest {

    @NotEmpty
    private String name;

    @NotNull
    private Float durationHours;

    @NotNull
    private Integer amount;

    @NotNull
    private BigDecimal price;
}
