package com.enesd.myshelfbackend.model.request;

import com.enesd.myshelfbackend.enums.ContentType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCollectionRequest {

    @NotEmpty
    private String name;

    @NotNull
    private ContentType contentType;

}
