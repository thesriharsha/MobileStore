package com.mobileshop.security;

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
public class JWTService {


    // To create a token
    public String generateToken(String username){
        Map<String,Object> claims = new HashMap<>();
        return createToken(claims,username);
    }

    private String createToken(Map<String, Object> claims, String username) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 500000))
                .signWith(getSecKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    private Key getSecKey() {
        byte[] keyBytes = Decoders.BASE64.decode("Mamil1RSbkL1rOi07IOj5MnX2B4KO3tT/SkTMp8oTsT74zSp8oZnX2B4kTMKsd1RSbkL1rOpbkTMF67NSgEmmjKlC");
        return Keys.hmacShaKeyFor(keyBytes);

    }

    // To validate provided Token
    public String extractUsername(String token) {
        return extractClaim(token, Claims:: getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims,T> claimsFunction) {
        final Claims claims = extractAllClaims(token);
        return claimsFunction.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSecKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token){
        return extractClaim(token, Claims :: getExpiration);
    }


}
