package com.enesd.myshelfbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSubscriptionDTO {
    private SubscriptionDTO subscription;
    private Instant endsAt;
    private boolean isActive;
}
