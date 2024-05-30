package com.enesd.myshelfbackend.dto;

import com.enesd.myshelfbackend.enums.ContentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishlistDTO {
    private Integer contentId;
    private ContentType contentType;
}
