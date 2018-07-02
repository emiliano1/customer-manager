package com.test.project.configuration;

import com.test.project.entity.User;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtToken {

    private static final Logger logger = LoggerFactory.getLogger(JwtToken.class);

    @Value("${security.jwt.secret}")
    private String secret;
    @Value("${security.jwt.expiration}")
    private String expiration;

    public String generateToken(Authentication authentication) {

        User loggedUser = (User) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = Date.from(now.toInstant().plusMillis(Long.valueOf(expiration)));

        return Jwts.builder().setSubject(Long.toString(loggedUser.getId()))
                .setIssuedAt(new Date()).setExpiration(expiryDate).signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Long getUserId(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }

}
