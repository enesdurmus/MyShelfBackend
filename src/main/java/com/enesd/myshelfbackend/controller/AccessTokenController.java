package com.enesd.myshelfbackend.controller;

import com.enesd.myshelfbackend.dto.AccessTokenDTO;
import com.enesd.myshelfbackend.model.entities.User;
import com.enesd.myshelfbackend.model.response.GenericResponse;
import com.enesd.myshelfbackend.services.AccessTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/access_tokens")
@AllArgsConstructor
public class AccessTokenController {

    private final AccessTokenService accessTokenService;

    @PreAuthorize("hasAuthority('DEVELOPER') or hasAuthority('ADMIN')")
    @PostMapping()
    public ResponseEntity<GenericResponse<AccessTokenDTO>> createAccessToken() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(GenericResponse.success(accessTokenService.createAccessToken(user)));
    }

    @PreAuthorize("hasAuthority('DEVELOPER') or hasAuthority('ADMIN')")
    @GetMapping()
    public ResponseEntity<GenericResponse<AccessTokenDTO>> getAccessToken() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(GenericResponse.success(accessTokenService.getAccessToken(user)));
    }
}
