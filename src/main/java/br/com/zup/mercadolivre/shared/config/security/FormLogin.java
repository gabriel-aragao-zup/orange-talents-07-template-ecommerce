package br.com.zup.mercadolivre.shared.config.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class FormLogin {
    
    private String login;
    private String password;

    public FormLogin(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public UsernamePasswordAuthenticationToken toAtuhenticationToken() {
        return new UsernamePasswordAuthenticationToken(login, password);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
