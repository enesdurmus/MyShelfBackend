package com.enesd.myshelfbackend.model.request;

import com.enesd.myshelfbackend.enums.ContentType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCollectionRequest {

    @NotEmpty(message = "Name can not be empty")
    private String name;

    @NotNull(message = "Content type can not be null")
    private ContentType contentType;

}
