package com.enesd.myshelfbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppSettingDTO {
    private Integer id;
    private String settingKey;
    private String settingValue;
    private Instant createdAt;
    private Instant updatedAt;
}
