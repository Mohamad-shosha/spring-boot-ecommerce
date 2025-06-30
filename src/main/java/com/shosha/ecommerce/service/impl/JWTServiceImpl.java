package com.shosha.ecommerce.service.impl;

import com.shosha.ecommerce.dto.UserDTO;
import com.shosha.ecommerce.service.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

@Service
public class JWTServiceImpl implements JWTService {

    private static final Logger log = LoggerFactory.getLogger(JWTServiceImpl.class);

    private static final long ACCESS_TOKEN_VALIDITY_MS = 1000 * 60 * 60 * 24 * 7;
    private static final long REFRESH_TOKEN_VALIDITY_MS = 1000 * 60 * 60 * 24 * 20; // 20 days

    @Value("${jwt.secret}")
    private String secret;

    private ConcurrentHashMap<String, Boolean> tokenBlacklist = new ConcurrentHashMap<>();

    @Override
    public String generateToken(UserDetails userDetails, UserDTO account) {
        log.debug("Generating token for UserDetails: {}", userDetails);
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("role", account.getRole());
        return createToken(claims, userDetails.getUsername(), ACCESS_TOKEN_VALIDITY_MS);
    }

    @Override
    public String generateToken(UserDTO account) {
        log.debug("Generating token for UserDTO: {}", account);
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("role", account.getRole());
        return createToken(claims, account.getEmail(), ACCESS_TOKEN_VALIDITY_MS);
    }

    @Override
    public String generateRefreshToken(HashMap<String, Object> extraClaims, UserDTO user) {
        log.debug("Generating refresh token for UserDTO: {}", user);
        return createToken(extraClaims, user.getEmail(), REFRESH_TOKEN_VALIDITY_MS);
    }

    @Override
    public String extractUserName(String token) {
        log.debug("Extracting username from token");
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUserName(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token) && !isTokenBlacklisted(token);
    }

    private String createToken(Map<String, Object> claims, String subject, long validityMs) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + validityMs);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getPayload();
    }

    private Key getSigningKey() {
        byte[] decodedKey = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(decodedKey);
    }

    @Override
    public void disableToken(String token) {
        tokenBlacklist.put(token, true);
    }

    public boolean isTokenBlacklisted(String token) {
        return tokenBlacklist.containsKey(token);
    }
}
