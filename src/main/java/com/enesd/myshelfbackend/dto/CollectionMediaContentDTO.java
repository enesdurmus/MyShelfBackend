package com.enesd.myshelfbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionMediaContentDTO {
    private Long collectionId;
    private Long mediaContentId;
    private Instant createdAt;
}