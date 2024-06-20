package com.enesd.myshelfbackend.services;

import com.enesd.myshelfbackend.consts.CacheNames;
import com.enesd.myshelfbackend.dto.AppSettingDTO;
import com.enesd.myshelfbackend.model.entities.AppSetting;
import com.enesd.myshelfbackend.model.request.CreateAppSettingRequest;
import com.enesd.myshelfbackend.repository.jpa.AppSettingRepository;
import com.enesd.myshelfbackend.utils.CustomModelMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AppSettingService {
    private final AppSettingRepository appSettingRepository;
    private final CustomModelMapper modelMapper;
    private final AppSettingService appSettingService;

    @Cacheable(value = CacheNames.APP_SETTINGS)
    public List<AppSettingDTO> getAppSettings() {
        List<AppSetting> appSettings = appSettingRepository.findAll();
        return modelMapper.mapAll(appSettings, AppSettingDTO.class);
    }

    public AppSettingDTO getAppSettingById(Integer id) {
        Optional<AppSettingDTO> appSetting = appSettingService.getAppSettings().stream().filter(appSettingDTO -> appSettingDTO.getId().equals(id)).findFirst();
        if (appSetting.isPresent()) {
            return appSetting.get();
        }
        throw new EntityNotFoundException("");
    }

    @CacheEvict(value = CacheNames.APP_SETTINGS, allEntries = true)
    public AppSettingDTO createAppSetting(CreateAppSettingRequest createAppSettingRequest) {
        AppSetting appSetting = new AppSetting();
        appSetting.setSettingKey(createAppSettingRequest.getSettingKey());
        appSetting.setSettingValue(createAppSettingRequest.getSettingValue());
        appSettingRepository.save(appSetting);
        return modelMapper.map(appSetting, AppSettingDTO.class);
    }

    @CacheEvict(value = CacheNames.APP_SETTINGS, allEntries = true)
    public AppSettingDTO updateAppSetting(CreateAppSettingRequest createAppSettingRequest) {
        return null;
    }

    @CacheEvict(value = CacheNames.APP_SETTINGS, allEntries = true)
    public void deleteAppSetting(Integer id) {
        appSettingRepository.delete(appSettingRepository.getReferenceById(id));
    }
}
