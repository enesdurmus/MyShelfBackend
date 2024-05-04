package com.enesd.myshelfbackend.model.base;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class Book {
    @Id
    private int id;

    private String isbn;
    private String title;
    private String titleWithoutSeries;
    private String countryCode;
    private Float averageRating;
    private int pageNumber;
    private String description;
    private String publisher;
    private String[] similarBooks;
    private String imageUrl;
    private String publicationDay;
    private String publicationMonth;
    private String publicationYear;
    private int[] bookAuthors;
}
