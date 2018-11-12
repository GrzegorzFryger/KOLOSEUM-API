package workerboard.security.jwt;


import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import workerboard.security.jwt.model.JwtUserPrinciple;

import java.util.Date;

@Component
public class JwtProvider {

    @Value("${jwt.secretPass}")
    private String secretPass;

    @Value("${jwt.expirationTime}")
    private int expirationTime;

    public String generateJwtToken(Authentication authentication) {

        JwtUserPrinciple userPrincipal = (JwtUserPrinciple) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTime);


        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secretPass)
                .compact();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(secretPass).
                    parseClaimsJws(authToken);
            return true;

        } catch ( SignatureException | MalformedJwtException | ExpiredJwtException |
                UnsupportedJwtException | IllegalArgumentException e ) {

            e.printStackTrace();
        }

        return false;
    }

    public String getEmailFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretPass)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }


}
