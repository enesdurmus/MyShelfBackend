package com.enesd.myshelfbackend.model.entities;

import com.enesd.myshelfbackend.model.compositeKeys.UserBookReadId;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Data
@Table(name = "user_books_read")
public class UserBookRead {

    @EmbeddedId
    private UserBookReadId id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("userId")
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("bookId")
//    @JoinColumn(name = "book_id")
//    private Book book;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;
}
