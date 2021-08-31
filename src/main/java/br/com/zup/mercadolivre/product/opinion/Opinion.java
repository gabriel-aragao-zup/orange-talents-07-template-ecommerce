package br.com.zup.mercadolivre.product.opinion;

import br.com.zup.mercadolivre.product.Product;
import br.com.zup.mercadolivre.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Opinion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer grade;
    private String title;
    @Lob
    @Size(max = 500)
    private String description;

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;

    public Opinion(@NotNull @Size(min = 1, max = 5) Integer grade, @NotBlank String title, @NotBlank @Size(max = 500) String description, User user, Product product) {
        this.grade = grade;
        this.title = title;
        this.description = description;
        this.user = user;
        this.product = product;
    }

    public String getTitle() {
        return title;
    }
}
