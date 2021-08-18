package br.com.zup.mercadolivre.shared.config.security;

import br.com.zup.mercadolivre.user.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;

import java.util.Date;


public class JWTToken implements Token{

    private String value;

    private String secret = "teste";
    private Long expirationTime=86400000L;

    public JWTToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Date issuedAt = new Date();
        Date expiresOn = new Date(issuedAt.getTime()+expirationTime);
        this.value = Jwts.builder()
                .setIssuer("Mercado Livre ZUP")
                .setSubject(user.getId().toString())
                .setIssuedAt(issuedAt)
                .setExpiration(expiresOn)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String getValue() {
        return value;
    }
}
