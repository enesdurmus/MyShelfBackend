package com.enesd.myshelfbackend.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "books")
public class Book {

    @Id
    @Column(name = "isbn")
    private String isbn;

    @Column(name = "title")
    private String title;

    @Column(name = "title_without_series")
    private String titleWithoutSeries;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "average_rating")
    private Float averageRating;

    @Column(name = "page_number")
    private int pageNumber;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "publication_day")
    private String publicationDay;

    @Column(name = "publication_month")
    private String publicationMonth;

    @Column(name = "publication_year")
    private String publicationYear;
}
