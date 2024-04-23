package com.enesd.myshelfbackend.model.compositeKeys;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBookReadId implements Serializable {
    private UUID userId;
    private int bookId;
}
