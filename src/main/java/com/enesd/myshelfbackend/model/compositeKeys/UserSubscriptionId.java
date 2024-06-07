package com.enesd.myshelfbackend.model.compositeKeys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSubscriptionId {
    private UUID user;
    private Long subscription;
}
