package com.enesd.myshelfbackend.security.services;

import com.enesd.myshelfbackend.dto.TokenDTO;
import com.enesd.myshelfbackend.model.entities.RefreshToken;
import com.enesd.myshelfbackend.model.exceptions.TokenRefreshException;
import com.enesd.myshelfbackend.repository.jpa.RefreshTokenRepository;
import com.enesd.myshelfbackend.repository.jpa.UserRepository;
import com.enesd.myshelfbackend.security.jwt.JwtUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TokenService {
    //@Value("${security.jwt.refresh-expiration-time}")
    // private Long refreshTokenDurationMs;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public TokenDTO refreshAccessToken(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByRefreshToken(token).orElseThrow(RuntimeException::new);
        verifyExpiration(refreshToken);
        return new TokenDTO(jwtUtil.generateToken(refreshToken.getUser()), refreshToken.getRefreshToken(), refreshToken.getExpiredAt());
    }

    public TokenDTO createRefreshToken(UUID userId) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(userRepository.getReferenceById(userId));
        refreshToken.setExpiredAt(Instant.now().plusMillis(350000000));
        refreshToken.setRefreshToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepository.saveRefreshToken(refreshToken.getUser().getId(), refreshToken.getRefreshToken(), refreshToken.getExpiredAt());
        return new TokenDTO(jwtUtil.generateToken(refreshToken.getUser()), refreshToken.getRefreshToken(), refreshToken.getExpiredAt());
    }

    public void verifyExpiration(RefreshToken token) {
        if (token.getExpiredAt().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException("Refresh token was expired");
        }
    }

    @Transactional
    public int deleteByUserId(Long userId) {
        return 0;//refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
    }
}