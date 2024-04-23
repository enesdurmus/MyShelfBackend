package com.enesd.myshelfbackend.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "books")
public class Book {

    @Id
    @Column(name = "book_id")
    private int Id;

    @Column(name = "isbn", unique = true)
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

    @Column(name = "description")
    private String description;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "similar_books")
    private String[] similarBooks;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "publication_day")
    private String publicationDay;

    @Column(name = "publication_month")
    private String publicationMonth;

    @Column(name = "publication_year")
    private String publicationYear;

    @Column(name = "authors")
    private int[] bookAuthors;
}
