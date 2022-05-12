package univ.tuit.backend.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import univ.tuit.backend.store.jpo.UserJpo;

import java.util.Date;

@Component
public class JwtProvider {

    @Value("${jwt.secretkey}")
    private String secretKey;

    @Value("${jwt.expireDateInMilliSeconds}")
    private Long expireDate;

    public String generateJwtToken(UserJpo userJpo) {
        return Jwts.builder()
                .setSubject(userJpo.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expireDate))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            System.err.println("Muddati o'tgan");
        } catch (MalformedJwtException malformedJwtException) {
            System.err.println("Buzilgan token");
        } catch (SignatureException signatureException) {
            System.err.println("Kalit so'z xato");
        } catch (UnsupportedJwtException unsupportedJwtException) {
            System.err.println("Qo'llanilmagan token");
        } catch (IllegalArgumentException illegalArgumentException) {
            System.err.println("Bo'sh token");
        }
        return false;
    }

    public Integer getUserIdFromToken(String token) {
        String subject = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return Integer.parseInt(subject);
    }
}
