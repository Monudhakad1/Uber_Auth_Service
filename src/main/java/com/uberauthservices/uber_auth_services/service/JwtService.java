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

    @Value("${jwt.expiry}")
    private int expiry;

    @Value("${jwt.secret}")
    private String SECRET;

    /**
     * Creates a JWT token with custom payload and subject (email)
     */
    public String createToken(Map<String, Object> payload, String email) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiry * 1000L);
        return Jwts.builder()
                .setClaims(payload) // ✅ Corrected from .claim(payload)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .setSubject(email)
                .signWith(getSignKey(), SignatureAlgorithm.HS256) // ✅ Specify algorithm explicitly
                .compact();
    }

    /**
     * Overloaded method to create token with empty payload
     */
    public String createToken(String email) {
        return createToken(new HashMap<>(), email);
    }

    /**
     * Extract all claims from the JWT token
     */
    public Claims extractAllPayloads(String token) {
        return Jwts
                .parserBuilder() // ✅ Changed from parser() to parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Extract any specific claim using a resolver function
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllPayloads(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extract expiration date from the token
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extract subject (email) from the token
     */
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Check if the token is expired
     */
    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Returns the signing key used to sign/verify JWT
     */
    public Key getSignKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Validates a JWT token for a given email
     */
    public Boolean validateToken(String token, String email) {
        final String userEmailFetchedFromToken = extractEmail(token);
        return (userEmailFetchedFromToken.equals(email)) && !isTokenExpired(token);
    }

    /**
     * Extract a custom claim/payload by key
     */
    public Object extractPayload(String token, String payloadKey) {
        Claims claim = extractAllPayloads(token);
        return claim.get(payloadKey);
    }

    /**
     * For testing token generation and payload extraction via Spring Boot CLI
     */
    @Override
    public void run(String... args) {
        Map<String, Object> mp = new HashMap<>();
        mp.put("email", "a@b.com");
        mp.put("phoneNumber", "9999999999");
        String result = createToken(mp, "sanket");
        System.out.println("Generated token is: " + result);
        System.out.println("Extracted email from payload: " + extractPayload(result, "email"));
    }
}
