package br.com.zup.mercadolivre.product;

import br.com.zup.mercadolivre.categoria.Category;
import br.com.zup.mercadolivre.product.features.Feature;
import br.com.zup.mercadolivre.product.opinion.Opinion;
import br.com.zup.mercadolivre.product.opinion.RepositoryOpinion;
import br.com.zup.mercadolivre.product.question.Question;
import br.com.zup.mercadolivre.product.question.RepositoryQuestion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DetailProductDTO {

    private String name;
    private BigDecimal price;
    private Boolean avaiable;
    private List<Feature> features;
    private String description;
    private List<String> categories;
    private String productOwner;
    private Set<String> images;
    private List<Opinion> opinions;
    private List<Question> questions;
    private Integer totalGrades;
    private Double avgGrade;

    public DetailProductDTO(Product product, List<Opinion> opinions,
                            List<Question> questions) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.avaiable = product.getQuantity()>0;
        this.features = product.getFeatures();
        this.description = product.getDescription();
        this.productOwner = product.getUser().getUsername();
        this.images = product.getImages();
        this.opinions = opinions;
        this.questions = questions;
        this.totalGrades = opinions.size();
        this.avgGrade = opinions.stream().map(Opinion::getGrade).collect(Collectors.toList()).stream()
                .mapToDouble(grade -> grade)
                .average().orElse(0);

        categories = new ArrayList<>();
        Category category = product.getCategory();
        categories.add(category.getName());
        while (category.getMotherCategory() != null){
            category = category.getMotherCategory();
            categories.add(category.getName());
        }

        Collections.reverse(categories);
    }

    public static DetailProductDTO from(Product product, RepositoryQuestion repositoryQuestion, RepositoryOpinion repositoryOpinion) {
        List<Opinion> opinions = repositoryOpinion.findByProduct_id(product.getId());
        List<Question> questions = repositoryQuestion.findByProduct_id(product.getId());

        return new DetailProductDTO(product, opinions, questions);
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Boolean getAvaiable() {
        return avaiable;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getCategories() {
        return categories;
    }

    public String getProductOwner() {
        return productOwner;
    }

    public Set<String> getImages() {
        return images;
    }

    public Integer getTotalGrades() {
        return totalGrades;
    }

    public Double getAvgGrade() {
        return avgGrade;
    }

    public List<Opinion> getOpinions() {
        return opinions;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
