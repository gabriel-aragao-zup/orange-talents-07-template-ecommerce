package br.com.zup.mercadolivre.product.opinion;

import br.com.zup.mercadolivre.product.Product;
import br.com.zup.mercadolivre.user.User;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.expression.spel.ast.OpInc;

import javax.validation.constraints.*;

public class FormOpinion {

    @NotNull
    @Min(1)
    @Max(5)
    private Integer grade;

    @NotBlank
    private String title;

    @NotBlank
    @Size(max = 500)
    private String description;


    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public FormOpinion(Integer grade, String title, String description) {
        this.grade = grade;
        this.title = title;
        this.description = description;
    }

    public Opinion toModel(User user, Product product){
        return new Opinion(grade, title, description, user, product);
    }

    public Integer getGrade() {
        return grade;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
