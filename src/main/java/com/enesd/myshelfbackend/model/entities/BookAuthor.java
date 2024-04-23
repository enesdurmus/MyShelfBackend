package com.enesd.myshelfbackend.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "book_authors")
public class BookAuthor {

    @Id
    @Column(name = "author_id")
    private int authorId;

    @Column(name = "author_name")
    private String authorName;
}
