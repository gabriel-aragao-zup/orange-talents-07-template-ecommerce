package br.com.zup.mercadolivre.product.question;

import br.com.zup.mercadolivre.product.Product;
import br.com.zup.mercadolivre.user.User;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @NotNull
    @ManyToOne
    private User user;
    @NotNull
    @ManyToOne
    private Product product;

    public Question(String title, User user, Product product) {
        this.title = title;
        this.user = user;
        this.product = product;
    }

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }

    public Product getProduct() {
        return product;
    }
}
