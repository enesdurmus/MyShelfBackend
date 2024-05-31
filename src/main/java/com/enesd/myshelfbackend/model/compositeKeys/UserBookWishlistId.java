package com.enesd.myshelfbackend.model.compositeKeys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBookWishlistId implements Serializable {
    private UUID user;
    private int book;
}
