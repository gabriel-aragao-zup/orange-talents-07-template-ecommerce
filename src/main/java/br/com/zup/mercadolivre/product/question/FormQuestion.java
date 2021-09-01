package br.com.zup.mercadolivre.product.question;

import br.com.zup.mercadolivre.product.Product;
import br.com.zup.mercadolivre.user.User;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class FormQuestion {

    @NotBlank
    private String title;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public FormQuestion(String title) {
        this.title = title;
    }

    public Question toModel(User user, Product product){
        return new Question(title, user, product);
    }
}
