package com.enesd.myshelfbackend.model.compositeKeys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectionContentId {
    private Long collection;
    private Long content;
}
