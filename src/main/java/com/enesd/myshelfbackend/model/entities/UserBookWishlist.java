package com.enesd.myshelfbackend.model.entities;

import com.enesd.myshelfbackend.model.compositeKeys.UserBookWishlistId;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@IdClass(UserBookWishlistId.class)
@Table(name = "user_books_wishlist")
public class UserBookWishlist {

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
