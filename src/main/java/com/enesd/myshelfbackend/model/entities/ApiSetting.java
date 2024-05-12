package com.enesd.myshelfbackend.model.entities;

import com.enesd.myshelfbackend.model.abstracts.Auditable;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "api_settings")
public class ApiSetting extends Auditable {

    @Id
    @Column(name = "api_setting_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int apiSettingId;

    @Column(name = "setting_key")
    private String settingKey;

    @Column(name = "setting_value")
    private String settingValue;
}
