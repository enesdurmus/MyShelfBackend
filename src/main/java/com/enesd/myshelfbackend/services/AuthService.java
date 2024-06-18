package com.enesd.myshelfbackend.services;

import com.enesd.myshelfbackend.dto.TokenDTO;
import com.enesd.myshelfbackend.dto.SignInDTO;
import com.enesd.myshelfbackend.dto.UserDTO;
import com.enesd.myshelfbackend.enums.RoleType;
import com.enesd.myshelfbackend.model.entities.User;
import com.enesd.myshelfbackend.model.request.RefreshTokenRequest;
import com.enesd.myshelfbackend.model.request.SignInRequest;
import com.enesd.myshelfbackend.model.request.SignUpRequest;
import com.enesd.myshelfbackend.repository.jpa.UserRepository;
import com.enesd.myshelfbackend.security.services.TokenService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public SignInDTO signUp(SignUpRequest signUpRequest) {
        Set<RoleType> defaultRoles = determineDefaultRoles();

        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRoles(defaultRoles);
        userRepository.save(user);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getId(), user, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        TokenDTO tokenDTO = tokenService.createJwtAccessAndRefreshToken(user.getId());
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return new SignInDTO(userDTO, tokenDTO);
    }

    public SignInDTO signIn(SignInRequest signInRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();

        TokenDTO tokenDTO = tokenService.createJwtAccessAndRefreshToken(user.getId());
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return new SignInDTO(userDTO, tokenDTO);
    }

    public TokenDTO refreshToken(RefreshTokenRequest refreshTokenRequest) {
        return tokenService.refreshAccessToken(refreshTokenRequest.getRefreshToken());
    }

    private Set<RoleType> determineDefaultRoles() {
        Set<RoleType> defaultRoles = new HashSet<>();
        defaultRoles.add(RoleType.USER);
        return defaultRoles;
    }
}
