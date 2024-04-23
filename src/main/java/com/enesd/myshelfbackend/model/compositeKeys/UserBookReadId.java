package com.enesd.myshelfbackend.model.compositeKeys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
public class UserBookReadId implements Serializable {
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "book_id")
    private int bookId;
}
