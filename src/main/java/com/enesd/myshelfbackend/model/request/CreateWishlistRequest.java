package com.enesd.myshelfbackend.model.request;

import com.enesd.myshelfbackend.enums.ContentType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateWishlistRequest {

    @NotNull
    private Integer contentId;

    @NotNull
    private ContentType contentType;
}
