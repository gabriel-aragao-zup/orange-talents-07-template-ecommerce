package br.com.zup.mercadolivre.user;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String password;

    private LocalDateTime createdAt;

    @Deprecated
    public User() {
    }

    public User(String login, NonEncriptedPassword password) {
        this.login = login;
        this.password = password.encript();
    }

    @PrePersist
    private void setCreationDateTime() {
        this.createdAt = LocalDateTime.now();
    }

}
