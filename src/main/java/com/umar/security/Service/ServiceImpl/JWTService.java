package com.umar.security.Service.ServiceImpl;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

    private String secretkey = "";

    public JWTService(){
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            SecretKey key = keyGenerator.generateKey();
            secretkey = Base64.getEncoder().encodeToString(key.getEncoded());
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public String generateToken(String userName){
    
        Map<String,Object> map = new HashMap<>();
        return Jwts
              .builder()
              .claims()
              .add(map)
              .subject(userName)
              .issuedAt(new Date(System.currentTimeMillis()))
              .expiration(new Date(System.currentTimeMillis()+ 60*60*30))
              .and()
              .signWith(getKey())
              .compact();
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretkey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUserName(String token) {
        return getClaim(token, Claims::getSubject);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private <T> T getClaim(String token,Function<Claims, T> claimressolver){

        Claims claims = extractAllClaims(token);
        return claimressolver.apply(claims);
    }

    public boolean validateToken(String token) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'validateToken'");
        // Claims claims = extractAllClaims(token);
        Date date = getClaim(token, Claims::getExpiration);
        Date currdate = new Date();
        System.out.println("Expiraty date is "+date.toString());
        System.out.println("Current date is"+currdate.toString());
        return currdate.before(date);
    }

  

}
