package com.waa.lab2.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
@Component
public class JwtUtil {
    private final String secret="23525sdgsg";
    private final long expiration=5*60*60*60;
    private final long refreshExpiration=5*60*60*60*60;

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
    public Date getIssuedAtDateFromToken(String token){
        return getClaimFromToken(token,Claims::getIssuedAt);
    }
    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token,Claims::getExpiration);
    }
    public Boolean isTokenExpired(String token){
        final Date expiration=getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims=new HashMap<>();
        return doGenerateToken(claims,userDetails.getUsername());
    }
    private String doGenerateToken( Map<String, Object> claims,String subject){
        return Jwts.builder().setClaims(claims)
                .setSubject(subject).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(SignatureAlgorithm.HS512,secret).compact();
    }
    public String doGenerateToken(String subject){
        return Jwts.builder().setSubject(subject)
                .setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(SignatureAlgorithm.HS512,secret).compact();
    }
    public String generateRefreshToken(String subject){
        return Jwts.builder().setSubject(subject)
                .setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis()+refreshExpiration))
                .signWith(SignatureAlgorithm.HS512,secret).compact();
    }
    public String getSubject(String token){
        return getAllClaimsFromToken(token).getSubject();
    }
    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public String doGenerateRefreshToken(Map<String,Object> claims,String subject){
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(SignatureAlgorithm.HS512,secret).compact();
    }
    public String getUsernameFromToken(String token){
         try{
             return getAllClaimsFromToken(token).getSubject();
         }catch (Exception e){
             System.out.println(e.getMessage());
         }
         return "";
    }
}
