package com.enesd.myshelfbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UserFriendDTO {
    private UUID userId;
    private UUID friendId;
}
