package com.enesd.myshelfbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMediaContentWishlistDTO {
    private Integer mediaContentId;
    private UUID userId;
}
