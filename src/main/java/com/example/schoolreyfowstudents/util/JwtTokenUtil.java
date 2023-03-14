package com.example.schoolreyfowstudents.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.security.Key;


@Component
public class JwtTokenUtil {

    private final static Logger log = LoggerFactory.getLogger(JwtTokenUtil.class);
    private final String jwtSecret = "zdtlD3JK56m6wTTgsNFhqzjqP512lm2123113513nlm15161515";

    public String getUsername(String token) {
        var claims = Jwts.parserBuilder()
                .setSigningKey(jwtSecret.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validate(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(jwtSecret.getBytes())
                    .build()
                    .parseClaimsJws(token);

            return true;
        } catch (Exception exception) {
            return false;
        }

    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(jwtSecret));
    }
}
