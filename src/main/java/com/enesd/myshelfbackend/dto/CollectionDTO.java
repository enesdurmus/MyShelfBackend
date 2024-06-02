package com.enesd.myshelfbackend.dto;

import com.enesd.myshelfbackend.enums.ContentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionDTO {
    private Long id;
    private String name;
    private ContentType contentType;
    private UUID userId;
}
