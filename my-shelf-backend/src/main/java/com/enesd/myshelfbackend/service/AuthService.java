package com.enesd.myshelfbackend.service;

import com.enesd.myshelfbackend.dto.SignInDTO;
import com.enesd.myshelfbackend.dto.UserDTO;
import com.enesd.myshelfbackend.enums.RoleType;
import com.enesd.myshelfbackend.model.entity.User;
import com.enesd.myshelfbackend.model.request.SignInRequest;
import com.enesd.myshelfbackend.model.request.SignUpRequest;
import com.enesd.myshelfbackend.repository.UserRepository;
import com.enesd.myshelfbackend.security.jwt.JwtUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final ModelMapper modelMapper;

    public SignInDTO signUp(SignUpRequest signUpRequest) {
        Set<RoleType> defaultRoles = new HashSet<>();
        defaultRoles.add(RoleType.ROLE_USER);

        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRoles(defaultRoles);
        user = userRepository.save(user);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getId(), user, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtil.generateToken(user);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return new SignInDTO(userDTO, jwt);
    }

    public void signIn(SignInRequest signInRequest) {

    }
}
