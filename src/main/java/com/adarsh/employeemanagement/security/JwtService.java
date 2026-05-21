package com.adarsh.employeemanagement.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

    private static final String SECRET_KEY =
            "mysecretkeymysecretkeymysecretkey12";

    private Key getSigningKey() {

        return new SecretKeySpec(
                SECRET_KEY.getBytes(),
                SignatureAlgorithm.HS256
                        .getJcaName());
    }

    public String generateToken(
            String username,
            String role) {

        Map<String, Object> claims =
                new HashMap<>();

        claims.put("role", role);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(
                            System.currentTimeMillis()
                            + 1000 * 60 * 15))
                .signWith(
                        getSigningKey(),
                        SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(
            String username) {

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(
                            System.currentTimeMillis()
                            + 1000L * 60 * 60 * 24 * 7))
                .signWith(
                        getSigningKey(),
                        SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(
            String token) {

        return extractAllClaims(token)
                .getSubject();
    }

    public String extractRole(
            String token) {

        return extractAllClaims(token)
                .get("role", String.class);
    }

    public boolean isTokenValid(
            String token,
            String username) {

        return extractUsername(token)
                .equals(username)
                && !isTokenExpired(token);
    }

    private boolean isTokenExpired(
            String token) {

        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }

    private Claims extractAllClaims(
            String token) {

        return Jwts.parserBuilder()
                .setSigningKey(
                        getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}