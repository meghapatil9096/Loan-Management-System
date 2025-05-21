package com.neosoft.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secret;


    @Value("${jwt.expiration}")
    private long expiration;

    private Key getSigningKey(){
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(keyBytes,SignatureAlgorithm.HS512.getJcaName());
    }

//    Generated JWT token
//    role

//    public String generateToken(String username){
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis()+expiration))
//                .signWith(getSigningKey(),SignatureAlgorithm.HS512)
//                .compact();
//    }


//    Generated JWT token with email and role
    public String generateToken(UserDetails userDetails)
    {
        String email = userDetails.getUsername();   //assuming email is the username
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return Jwts.builder()
                .claim("email",email)   //add email as a claim
                .claim("roles",roles)   //add role as a claim
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();

    }



//    Get username/email from Jwt

//    public String getUsernameFromToken(String token){
//        return Jwts.parser()
//                .setSigningKey(getSigningKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//    }

        public String getEmailFromToken(String token){
            return Jwts.parser()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .get("email", String.class);
        }

//        Get Authorities from token
    @SuppressWarnings("unchecked")
    public List<SimpleGrantedAuthority> getAuthoritiesFromToken(String token)
    {
        List<String> roles = (List<String>) Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("roles");

        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }


//    Validation token
    public boolean validateToken(String token, String expectedEmail)
    {
        final String tokenEmail = getEmailFromToken(token);
        return tokenEmail.equals(expectedEmail) && !isTokenExpired(token);
    }

//    check if token is expired
    private boolean isTokenExpired(String token)
    {
        Date expirationDate = Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expirationDate.before(new Date());
    }

}





