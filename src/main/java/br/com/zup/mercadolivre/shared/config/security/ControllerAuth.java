package br.com.zup.mercadolivre.shared.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class ControllerAuth {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<?> authenticateUser(@RequestBody FormLogin formLogin) {
        UsernamePasswordAuthenticationToken authenticationToken = formLogin.toAtuhenticationToken();

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        Token token = new JWTToken(authentication);
        return ResponseEntity.ok().build();
    }

}
