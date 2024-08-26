package com.enesd.myshelfbackend.controller;

import com.enesd.myshelfbackend.dto.AppSettingDTO;
import com.enesd.myshelfbackend.enums.Role;
import com.enesd.myshelfbackend.model.request.CreateAppSettingRequest;
import com.enesd.myshelfbackend.model.response.GenericResponse;
import com.enesd.myshelfbackend.services.AppSettingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/app-settings")
@AllArgsConstructor
public class AppSettingController {

    private final AppSettingService appSettingService;

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<AppSettingDTO>> getAppSettingById(@PathVariable Integer id) {
        return ResponseEntity.ok(GenericResponse.success(appSettingService.getAppSettingById(id)));
    }

    @GetMapping
    public ResponseEntity<GenericResponse<List<AppSettingDTO>>> getAppSettings() {
        return ResponseEntity.ok(GenericResponse.success(appSettingService.getAppSettings()));
    }

    @Secured(value = {Role.Code.ADMIN})
    @PostMapping
    public ResponseEntity<GenericResponse<AppSettingDTO>> createAppSetting(@RequestBody @Validated CreateAppSettingRequest createAppSettingRequest) {
        return ResponseEntity.ok(GenericResponse.success(appSettingService.createAppSetting(createAppSettingRequest)));
    }

    @Secured(value = {Role.Code.ADMIN})
    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<AppSettingDTO>> updateSubscription(@RequestBody @Validated CreateAppSettingRequest createAppSettingRequest) {
        return ResponseEntity.ok(GenericResponse.success(appSettingService.updateAppSetting(createAppSettingRequest)));
    }

    @Secured(value = {Role.Code.ADMIN})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable Integer id) {
        appSettingService.deleteAppSetting(id);
        return ResponseEntity.noContent().build();
    }
}
