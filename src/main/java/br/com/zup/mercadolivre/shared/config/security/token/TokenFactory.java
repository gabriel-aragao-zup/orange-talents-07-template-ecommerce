package br.com.zup.mercadolivre.shared.config.security.token;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class TokenFactory {

    @Value("${forum.jwt.secret}")
    private String secret;
    public Token createJWTToken(Authentication authentication){
        return new JWTToken(authentication, secret);
    }

    public Token createBearerToken(Token token){
        return new BearerToken(token);
    }
}
