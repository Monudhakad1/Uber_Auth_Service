package com.uberauthservices.uber_auth_services.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService implements CommandLineRunner {

    @Value("${jwt.expiry}")  // e.g., 3600 (in seconds)
    private int expiry;

    @Value("${jwt.secret}")  // should be at least 32 chars for HMAC-SHA256
    private String SECRET;

    /*
     * Generate JWT Token with custom payload and subject (username)
     */
    private String createToken(Map<String, Object> payload, String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiry * 1000L);  // expiry in milliseconds

        return Jwts.builder()
                .setClaims(payload)
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /*
     * Extract all claims (payload)
     */
    private Claims extractPayload(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /*
     * Extract expiration from token
     */
    private Date extractExpiration(String token) {
        return extractPayload(token).getExpiration();
    }

    /*
     * Validate token is not expired
     */
    private Boolean validateToken(String token) {
        try {
            return !extractExpiration(token).before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    /*
     * Get signing key
     */
    private Key getSignKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    /*
     * Run on app start for testing
     */
    public void run(String... args) throws Exception {
        Map<String, Object> mp = new HashMap<>();
        mp.put("email", "a@b.com");
        mp.put("phoneNumber", "7987648160");

        String result = createToken(mp, "monu");
        System.out.println("Generated Token: " + result);

        // Validation test
        System.out.println("Is Valid: " + validateToken(result));
        System.out.println("Expiration: " + extractExpiration(result));
    }
}
