package com.enesd.myshelfbackend.services;

import com.enesd.myshelfbackend.enums.Role;
import com.enesd.myshelfbackend.model.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtService {
    public static final String AUTHORITIES_KEY = "authorities";

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time}")
    private long jwtExpirationInMillis;

    public String generateToken(User user) {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(AUTHORITIES_KEY, user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()));
        return generateToken(claims, user);
    }

    public String generateToken(Map<String, Object> extraClaims, User user) {
        return buildToken(extraClaims, user, getExpirationTime());
    }

    public long getExpirationTime() {
        return jwtExpirationInMillis;
    }

    private String buildToken(Map<String, Object> extraClaims, User user, long expiration) {
        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(user.getId().toString())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey())
                .compact();
    }

    public boolean isTokenValid(String token) {
        return !isTokenExpired(token);
    }

    public Authentication getAuthentication(String token) {
        Claims claims = extractAllClaims(token);
        UUID userId = UUID.fromString(claims.getSubject());

        List<String> rolesArr = claims.get(AUTHORITIES_KEY, List.class);
        Set<Role> roles = rolesArr.stream()
                .map(Role::fromString)
                .collect(Collectors.toSet());

        User user = new User();
        user.setId(userId);
        user.setRoles(roles);
        return new UsernamePasswordAuthenticationToken(user, userId, user.getAuthorities());
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
