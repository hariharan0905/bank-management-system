package com.bms.bank_management_system.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {

    private static final String RAW_SECRET = "a-string-secret-at-least-256-bits-long!!";

    // ✅ Token validity period: 1 hour
    private static final long EXPIRATION_TIME = 3600000;

    private Key secretKey;

    // ✅ Initializes secret key for signing and verification
    @PostConstruct
    public void init() {
        this.secretKey = Keys.hmacShaKeyFor(RAW_SECRET.getBytes());
        System.out.println("✅ JWT SECRET KEY LENGTH (bytes): " + RAW_SECRET.getBytes().length);
    }

    // ✅ Generates JWT for authenticated user with USER authority
    public String generateToken(String accountNumber) {
        return Jwts.builder()
                .setSubject(accountNumber)
                .claim("authorities", List.of("USER"))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    // ✅ Extracts username (accountNumber) from token
    public String getUsernameFromToken(String token) {
        return getClaims(token).getSubject();
    }

    // ✅ Extracts roles from token
    public List<String> getAuthorities(String token) {
        return getClaims(token).get("authorities", List.class);
    }

    // ✅ Validates token signature, expiration, and username
    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            String username = getUsernameFromToken(token);
            return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("❌ Token validation failed: " + e.getMessage());
            return false;
        }
    }

    // ✅ Returns all claims from token
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // ✅ Checks if token is expired
    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }
}
