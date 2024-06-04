package com.enesd.myshelfbackend.model.request;

import com.enesd.myshelfbackend.enums.ContentType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateReviewRequest {

    @NotNull
    private Long contentId;

    @NotNull
    private ContentType contentType;

    @Min(value = 1, message = "Rating can be a minimum of 1")
    @Max(value = 10, message = "Rating can be a maximum of 10")
    @NotNull
    private int rating;

    @NotNull
    private String review;
}
