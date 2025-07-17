package com.uberauthservices.uber_auth_services.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService implements CommandLineRunner {

    @Value("${jwt.expiry}")  // ✅ Corrected
    private int expiry;

    @Value("${jwt.secret}")  // ✅ Corrected
    private String SECRET;

    /*
     * This action creates a brand new JWT token for us based on a payload
     */
    private String createToken(Map<String, Object> payload, String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiry * 1000L);

        SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setClaims(payload)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .setSubject(username)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public  void run(String... args) throws Exception {
        Map<String,Object> mp = new HashMap<>();
        mp.put("email","a@b.com");
        mp.put("phoneNumber","7987648160");
        String result = createToken(mp,"monu");
        System.out.println(" Generated Tk: " + result);
    }

}
