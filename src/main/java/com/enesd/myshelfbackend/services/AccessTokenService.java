package com.enesd.myshelfbackend.services;

import com.enesd.myshelfbackend.dto.AccessTokenDTO;
import com.enesd.myshelfbackend.enums.RoleType;
import com.enesd.myshelfbackend.model.entities.AccessToken;
import com.enesd.myshelfbackend.model.entities.User;
import com.enesd.myshelfbackend.repository.jpa.AccessTokenRepository;
import com.enesd.myshelfbackend.repository.jpa.UserRepository;
import com.enesd.myshelfbackend.security.services.JwtService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

import static com.enesd.myshelfbackend.security.services.JwtService.AUTHORITIES_KEY;

@Service
@AllArgsConstructor
public class AccessTokenService {
    private final AccessTokenRepository accessTokenRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final ModelMapper modelMapper;

    public AccessTokenDTO createAccessToken(User user) {
        ArrayList<RoleType> roles = new ArrayList<>();
        roles.add(RoleType.DEVELOPER_USER);

        HashMap<String, Object> claims = new HashMap<>();
        claims.put(AUTHORITIES_KEY, roles);

        user = userRepository.getReferenceById(user.getId());
        String token = jwtService.generateToken(claims, user);
        AccessToken accessToken = new AccessToken();
        accessToken.setUser(user);
        accessToken.setToken(token);
        accessTokenRepository.save(accessToken);
        return modelMapper.map(accessToken, AccessTokenDTO.class);
    }

    public AccessTokenDTO getAccessToken(User user) {
        return modelMapper.map(accessTokenRepository.findById(user.getId()), AccessTokenDTO.class);
    }
}
