package br.com.zup.mercadolivre.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NonEncriptedPassword {

    @NotBlank
    @Size(min = 6)
    private String cleanPassword;

    public NonEncriptedPassword(String cleanPassword) {
        this.cleanPassword = cleanPassword;
    }

    public String encript(){
        return new BCryptPasswordEncoder().encode(cleanPassword);
    }
}
