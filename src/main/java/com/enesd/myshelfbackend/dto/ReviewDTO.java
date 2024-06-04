package com.enesd.myshelfbackend.dto;

import com.enesd.myshelfbackend.enums.ContentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private Long id;
    private ContentType contentType;
    private int rating;
    private String review;
    private Long bookId;
    private Long mediaContentId;
    private UUID userId;
}
