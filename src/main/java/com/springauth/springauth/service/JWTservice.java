package com.springauth.springauth.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTservice {

    private static final String SECRET_KEY = "fglksdjflkgjsdlfkgjksdlfglsdjrkgjslkrj";

    public String generateToken(String username) {

        Map<String, Object> claims = new HashMap<>();
        return Jwts
                .builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 30))
                .and()
                .signWith(getKey())
                .compact();
    }

    private Key getKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String extractUsername(String token) {
     return "";
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validateToken'");
    }

}
