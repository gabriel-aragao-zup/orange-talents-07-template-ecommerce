package br.com.zup.mercadolivre.product;

import br.com.zup.mercadolivre.categoria.Category;
import br.com.zup.mercadolivre.categoria.RepositoryCategory;
import br.com.zup.mercadolivre.shared.config.validation.beanvalidation.existsid.ExistsId;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FormProduct {

    @NotBlank
    private String name;
    @Positive
    @NotNull
    private BigDecimal price;
    @Positive
    @NotNull
    private Integer quantity;
    @Size(min = 3)
    private List<Feature> features;
    @NotBlank
    @Size(max = 1000)
    private String description;
    @NotNull
    @ExistsId(domainClass = Category.class, fieldName = "id")
    private Long categoryId;


    public FormProduct() {
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public FormProduct(String name, BigDecimal price, Integer quantidade, List<Feature> features, String descricao, Long categoriaId) {
        this.name = name;
        this.price = price;
        this.quantity = quantidade;
        this.features = features;
        this.description = descricao;
        this.categoryId = categoriaId;
    }

    public Product toModel(RepositoryCategory repositoryCategory){
        Category category = repositoryCategory.findById(categoryId).get();
        return new Product(name, price, quantity, features, description, category);
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public String getDescription() {
        return description;
    }

    public Long getCategoryId() {
        return categoryId;
    }
}
