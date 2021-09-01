package br.com.zup.mercadolivre.product.question;

import br.com.zup.mercadolivre.product.Product;
import br.com.zup.mercadolivre.product.RepositoryProduct;
import br.com.zup.mercadolivre.shared.config.email.FakeEmailSender;
import br.com.zup.mercadolivre.shared.config.validation.ValidationError;
import br.com.zup.mercadolivre.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "products/{id}/questions")
public class ControllerQuestion {

    private RepositoryProduct repositoryProduct;
    private RepositoryQuestion repositoryQuestion;

    @Autowired
    private FakeEmailSender fakeEmailSender;

    public ControllerQuestion(RepositoryProduct repositoryProduct, RepositoryQuestion repositoryQuestion) {
        this.repositoryProduct = repositoryProduct;
        this.repositoryQuestion = repositoryQuestion;
    }

    @PostMapping
    public ResponseEntity<?> createQuestion(@PathVariable("id") Long id,
                                            @RequestBody @Valid FormQuestion formQuestion,
                                            @AuthenticationPrincipal User user){
        Optional<Product> maybeProduct = repositoryProduct.findById(id);
        if(maybeProduct.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ValidationError("productId", "Product not found!"));
        }

        Product product = maybeProduct.get();

        Question question = formQuestion.toModel(user, product);

        repositoryQuestion.save(question);

        try {
            fakeEmailSender.send(new EmailQuestion(user, product.getUser(), product, question));
        } catch (Exception e) {
            e.printStackTrace();
        }


        return ResponseEntity.ok().build();
    }
}
