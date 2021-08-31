package br.com.zup.mercadolivre.product.opinion;

import br.com.zup.mercadolivre.product.Product;
import br.com.zup.mercadolivre.product.RepositoryProduct;
import br.com.zup.mercadolivre.shared.config.validation.ValidationError;
import br.com.zup.mercadolivre.user.User;
import org.apache.catalina.Store;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/products/{id}/opinions")
public class ControllerOpinion {

    private RepositoryProduct repositoryProduct;
    private RepositoryOpinion repositoryOpinion;

    public ControllerOpinion(RepositoryProduct repositoryProduct, RepositoryOpinion repositoryOpinion) {
        this.repositoryProduct = repositoryProduct;
        this.repositoryOpinion = repositoryOpinion;
    }

    @PostMapping
    public ResponseEntity<?> createOpinion(@PathVariable("id") Long id, @RequestBody @Valid FormOpinion formOpinion, @AuthenticationPrincipal User user){

        Optional<Product> maybeProduct = repositoryProduct.findById(id);
        if(maybeProduct.isEmpty()){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ValidationError("productId", "Product not found!"));
        }
        Product product = maybeProduct.get();

        Opinion opinion = formOpinion.toModel(user, product);

        repositoryOpinion.save(opinion);

        return ResponseEntity.ok().build();
    }
}
