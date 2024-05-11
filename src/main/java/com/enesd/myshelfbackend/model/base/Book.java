package com.enesd.myshelfbackend.model.base;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class Book extends Auditable {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "isbn", unique = true)
    private String isbn;

    @Column(name = "title", columnDefinition = "TEXT")
    private String title;

    @Column(name = "title_without_series", columnDefinition = "TEXT")
    private String titleWithoutSeries;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "average_rating")
    private Float averageRating;

    @Column(name = "page_number")
    private int pageNumber;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "publisher")
    private String publisher;

//    @Column(name = "similar_books")
//    private String[] similarBooks;

    @Column(name = "image_url", columnDefinition = "TEXT")
    private String imageUrl;

    @Column(name = "publication_day")
    private String publicationDay;

    @Column(name = "publication_month")
    private String publicationMonth;

    @Column(name = "publication_year")
    private String publicationYear;

//    @Column(name = "authors")
//    private int[] bookAuthors;
}
