package com.enesd.myshelfbackend.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private Long id;
    private String isbn;
    private String title;
    private String titleWithoutSeries;
    private String countryCode;
    private Float averageRating;
    private int pageNumber;
    private String description;
    private String publisher;
    private String imageUrl;
}
