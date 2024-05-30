package com.enesd.myshelfbackend.dto;

import com.enesd.myshelfbackend.enums.ActivityType;
import com.enesd.myshelfbackend.enums.ContentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserContentActivityDTO {
    private Long id;
    private UUID userId;
    private int contentId;
    private ContentType contentType;
    private ActivityType activityType;
}
