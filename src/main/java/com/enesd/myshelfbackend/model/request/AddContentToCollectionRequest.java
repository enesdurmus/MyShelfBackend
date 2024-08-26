package com.enesd.myshelfbackend.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddContentToCollectionRequest {
    @NotNull(message = "Collection id can not be null")
    private Long collectionId;

    @NotNull(message = "Content id can not be null")
    private Long contentId;
}
