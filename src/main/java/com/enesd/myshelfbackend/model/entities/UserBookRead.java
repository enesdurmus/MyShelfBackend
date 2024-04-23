package com.enesd.myshelfbackend.model.entities;

import com.enesd.myshelfbackend.model.compositeKeys.UserBookReadId;
import com.enesd.myshelfbackend.model.compositeKeys.UserBookWishlistId;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@IdClass(UserBookReadId.class)
@Table(name = "user_books_read")
public class UserBookRead {

    @Id
    @Column(name = "user_id")
    private UUID userId;

    @Id
    @Column(name = "book_id")
    private int bookId;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;
}
