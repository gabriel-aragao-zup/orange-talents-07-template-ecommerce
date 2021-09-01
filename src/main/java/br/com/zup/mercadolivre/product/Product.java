package br.com.zup.mercadolivre.product;

import br.com.zup.mercadolivre.categoria.Category;
import br.com.zup.mercadolivre.user.User;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
@TypeDef(name = "json", typeClass = JsonType.class)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer quantity;
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private List<Feature> features;
    @Lob
    @Size(max = 1000)
    private String description;
    @ManyToOne
    private Category category;
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne
    @NotNull
    private User user;

    @ElementCollection
    private Set<String> images = new HashSet<String>();

    @Deprecated
    public Product() {
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Product(String name, BigDecimal price, Integer quantity, List<Feature> features, String description, Category category, User user) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.features = features;
        this.description = description;
        this.category = category;
        this.user = user;
    }


    public User getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

    public void addImages(Set<String> images){
        this.images.addAll(images);
    }
}
