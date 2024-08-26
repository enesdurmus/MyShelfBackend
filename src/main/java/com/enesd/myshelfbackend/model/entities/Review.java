package com.enesd.myshelfbackend.model.entities;

import com.enesd.myshelfbackend.enums.ContentType;
import com.enesd.myshelfbackend.model.abstracts.Auditable;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "reviews", indexes = {
        @Index(columnList = "content_type, book_id, user_id", unique = true),
        @Index(columnList = "content_type, media_content_id, user_id", unique = true)})
public class Review extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "content_type")
    private ContentType contentType;

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "review", columnDefinition = "TEXT")
    private String review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private BookEntity book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "media_content_id")
    private MediaEntity mediaContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}