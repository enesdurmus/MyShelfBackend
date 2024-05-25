package com.enesd.myshelfbackend.controller;

import com.enesd.myshelfbackend.dto.TokenDTO;
import com.enesd.myshelfbackend.dto.SignInDTO;
import com.enesd.myshelfbackend.model.request.RefreshTokenRequest;
import com.enesd.myshelfbackend.model.request.SignInRequest;
import com.enesd.myshelfbackend.model.request.SignUpRequest;
import com.enesd.myshelfbackend.model.response.GenericResponse;
import com.enesd.myshelfbackend.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/user/signup")
    public ResponseEntity<GenericResponse<SignInDTO>> signUp(@Validated @RequestBody SignUpRequest signUpRequest) {
        SignInDTO signInDTO = authService.signUp(signUpRequest);
        return ResponseEntity.ok(GenericResponse.success(signInDTO));
    }

    @PostMapping("/user/signin")
    public ResponseEntity<GenericResponse<SignInDTO>> signIn(@Validated @RequestBody SignInRequest signInRequest) {
        SignInDTO signInDTO = authService.signIn(signInRequest);
        return ResponseEntity.ok(GenericResponse.success(signInDTO));
    }

    @PostMapping("/user/refresh_token")
    public ResponseEntity<GenericResponse<TokenDTO>> refreshToken(@Validated @RequestBody RefreshTokenRequest refreshTokenRequest) {
        TokenDTO tokenDTO = authService.refreshToken(refreshTokenRequest);
        return ResponseEntity.ok(GenericResponse.success(tokenDTO));
    }
}
