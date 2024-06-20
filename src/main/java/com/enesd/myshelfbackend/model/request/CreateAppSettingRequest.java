package com.enesd.myshelfbackend.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CreateAppSettingRequest {
    @NotEmpty(message = "Setting key must not be null")
    private String settingKey;

    @NotEmpty(message = "Setting value must not be null")
    private String settingValue;
}
