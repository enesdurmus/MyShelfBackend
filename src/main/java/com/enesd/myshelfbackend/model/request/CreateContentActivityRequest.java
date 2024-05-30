package com.enesd.myshelfbackend.model.request;

import com.enesd.myshelfbackend.enums.ActivityType;
import com.enesd.myshelfbackend.enums.ContentType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class CreateContentActivityRequest {

    @NotNull
    private int contentId;

    @NotNull
    private ContentType contentType;

    @NotNull
    private ActivityType activityType;
}
