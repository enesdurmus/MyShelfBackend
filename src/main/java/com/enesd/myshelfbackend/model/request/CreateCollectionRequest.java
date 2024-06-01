package com.enesd.myshelfbackend.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CreateCollectionRequest {

    @NotEmpty
    private String name;
}
