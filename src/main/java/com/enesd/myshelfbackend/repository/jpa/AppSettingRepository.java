package com.enesd.myshelfbackend.repository.jpa;

import com.enesd.myshelfbackend.model.entities.AppSetting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppSettingRepository extends JpaRepository<AppSetting, Integer> {
}
