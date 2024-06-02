package com.enesd.myshelfbackend.model.compositeKeys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCollectionId implements Serializable {
    private Long collection;
    private Long book;
}
