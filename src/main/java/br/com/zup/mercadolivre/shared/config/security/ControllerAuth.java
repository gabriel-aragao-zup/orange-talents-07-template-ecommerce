package br.com.zup.mercadolivre.shared.config.security;

import br.com.zup.mercadolivre.shared.config.security.token.JWTToken;
import br.com.zup.mercadolivre.shared.config.security.token.Token;
import br.com.zup.mercadolivre.shared.config.security.token.TokenFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class ControllerAuth {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenFactory tokenFactory;

    @PostMapping
    public ResponseEntity<?> authenticateUser(@RequestBody FormLogin formLogin) {
        UsernamePasswordAuthenticationToken authenticationToken = formLogin.toAtuhenticationToken();

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        Token token = tokenFactory.createJWTToken(authentication);
        return ResponseEntity.ok().body(tokenFactory.createBearerToken(token));
    }

}
