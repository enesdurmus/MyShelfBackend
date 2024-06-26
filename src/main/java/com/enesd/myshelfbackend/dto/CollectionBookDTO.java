package com.enesd.myshelfbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionBookDTO {
    private Long collectionId;
    private Long bookId;
    private Instant createdAt;
}
