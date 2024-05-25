package com.enesd.myshelfbackend.model.compositeKeys;

import com.enesd.myshelfbackend.enums.ContentType;
import com.enesd.myshelfbackend.model.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishlistId implements Serializable {
    private User user;
    private int contentId;
    private ContentType contentType;
}
