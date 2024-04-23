package com.enesd.myshelfbackend.controller;

import com.enesd.myshelfbackend.dto.SignInDTO;
import com.enesd.myshelfbackend.model.request.SignInRequest;
import com.enesd.myshelfbackend.model.request.SignUpRequest;
import com.enesd.myshelfbackend.model.response.GenericResponse;
import com.enesd.myshelfbackend.service.AuthService;
import lombok.AllArgsConstructor;
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
    public GenericResponse signUp(@Validated @RequestBody SignUpRequest signUpRequest) {
        SignInDTO signInDTO = authService.signUp(signUpRequest);
        return GenericResponse.success(signInDTO);
    }

    @PostMapping("/user/signin")
    public GenericResponse signIn(@Validated @RequestBody SignInRequest signInRequest) {
        SignInDTO signInDTO = authService.signIn(signInRequest);
        return GenericResponse.success(signInDTO);
    }
}
