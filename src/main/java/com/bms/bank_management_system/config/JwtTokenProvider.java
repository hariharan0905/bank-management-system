package com.bms.bank_management_system.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {

    private static final String RAW_SECRET = "YourSuperSecureSecretKeyWithMoreThan32Characters";
    private static final long EXPIRATION_TIME = 3600000; // 1 hour

    private Key secretKey;

    @PostConstruct
    public void init() {
        byte[] decodedKey = Base64.getEncoder().encode(RAW_SECRET.getBytes());
        this.secretKey = Keys.hmacShaKeyFor(decodedKey);
    }

    // ✅ Generate JWT with role claim
    public String generateToken(String accountNumber) {
        return Jwts.builder()
                .setSubject(accountNumber)
                .claim("authorities", List.of("USER")) // You can customize this
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    // ✅ Extract account number (username)
    public String getUsernameFromToken(String token) {
        return getClaims(token).getSubject();
    }

    // ✅ Extract authorities
    public List<String> getAuthorities(String token) {
        return getClaims(token).get("authorities", List.class);
    }

    // ✅ Validate token
    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            String username = getUsernameFromToken(token);
            return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // ✅ Get token claims
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }
}
