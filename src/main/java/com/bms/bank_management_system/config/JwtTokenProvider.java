package com.bms.bank_management_system.config;

import java.util.Base64;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {
    private static final String SECRET_KEY = Base64.getEncoder().encodeToString("YourSuperSecureSecretKeyWithMoreThan32Characters".getBytes());

    @SuppressWarnings("deprecation")
    public String generateToken(String accountNumber) {
        return Jwts.builder()
            .setSubject(accountNumber)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24 hours
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
            .compact();
    }
}
