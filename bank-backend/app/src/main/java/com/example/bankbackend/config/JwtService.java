package com.example.bankbackend.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {
    private static final String SECRET_KEY = "MzR5cTJFOHlWZXVGSHlTeW5tVGtpd1JpcTNiTVRKdXNyL0N4dVVObkZoQnlZSm1pSTlsU2lwck" +
            "dNQk95WlZBa1B1bExnTU1rL1pGWlFraW8xS2dWN3FYRnlVSHFzTXB2bE5na054eHpsTzJXOVN6cjJPcnpsVVl2bjdPMG13TzZ5UlphUHdua" +
            "2t4OUxYVlFOaDliMmkrVWcybk0yYitKN0EyQUVMNFJtL0xQdHo5REZyM0EzWk9uK2l3dlJ6ZnJsRDY0WDJMUnByUXZ3dzh5aHhEVFRiMU8v" +
            "dVlwcWRUWjY3RVo3U2VoR2NpaSt2a2NUSDhMaGY5Y0hRZHp0eXh2TTVaT3k3cWJJU01QR21qTVJPZmlGcmZEWUFGRDU4elJFekovQ3NVNEV" +
            "VTWtrNTZjOWZoUUtLM0R3U1JXcjVzcXczdGcxRklkdExzWGRHL3p6eDRpUEp0aUhESktMb2VyRkhNV2lLVUFvenhJPQ==";
    public static final int expiration = 1000 * 60 * 60 * 24;

    public String generateToken(Map<String, Object> extraClaims,
                                UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }
}
