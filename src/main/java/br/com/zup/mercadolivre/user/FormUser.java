package br.com.zup.mercadolivre.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class FormUser {

    @NotBlank
    @Email
    private String login;
    @NotBlank
    @Size(min = 6)
    private String password;

    public FormUser(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User toModel(){
        return new User(login, new NonEncriptedPassword(password));
    }
}
