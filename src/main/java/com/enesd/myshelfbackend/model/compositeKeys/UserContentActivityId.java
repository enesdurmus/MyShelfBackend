package com.enesd.myshelfbackend.model.compositeKeys;

import com.enesd.myshelfbackend.enums.ContentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserContentActivityId implements Serializable {
    private UUID userId;
    private int contentId;
    private ContentType contentType;
}
