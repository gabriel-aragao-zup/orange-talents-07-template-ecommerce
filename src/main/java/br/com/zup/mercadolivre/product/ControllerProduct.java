package br.com.zup.mercadolivre.product;

import br.com.zup.mercadolivre.categoria.RepositoryCategory;
import br.com.zup.mercadolivre.product.features.NoDuplicatedFeatureValidator;
import br.com.zup.mercadolivre.product.images.EmulatedUploader;
import br.com.zup.mercadolivre.product.images.FormImages;
import br.com.zup.mercadolivre.product.opinion.RepositoryOpinion;
import br.com.zup.mercadolivre.product.question.RepositoryQuestion;
import br.com.zup.mercadolivre.shared.config.validation.ValidationError;
import br.com.zup.mercadolivre.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/products")
public class ControllerProduct {

    private RepositoryProduct repositoryProduct;
    private RepositoryCategory repositoryCategory;
    @Autowired
    private NoDuplicatedFeatureValidator noDuplicatedFeatureValidator;
    @Autowired
    private EmulatedUploader emulatedUploader;
    private RepositoryQuestion repositoryQuestion;
    private RepositoryOpinion repositoryOpinion;

    public ControllerProduct(RepositoryProduct repositoryProduct,
                             RepositoryCategory repositoryCategory,
                             RepositoryQuestion repositoryQuestion,
                             RepositoryOpinion repositoryOpinion) {
        this.repositoryProduct = repositoryProduct;
        this.repositoryCategory = repositoryCategory;
        this.repositoryQuestion = repositoryQuestion;
        this.repositoryOpinion = repositoryOpinion;
    }

    @InitBinder(value = "FormProduct")
    public void init(WebDataBinder webDataBinder){
        webDataBinder.addValidators(noDuplicatedFeatureValidator);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody @Valid FormProduct formProduct, @AuthenticationPrincipal User user){
        Product product = formProduct.toModel(repositoryCategory, user);
        repositoryProduct.save(product);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/{id}/images")
    public ResponseEntity<?> addImages(@PathVariable("id") Long id, @Valid FormImages formImages, @AuthenticationPrincipal User user){
        Optional<Product> maybeProduct = repositoryProduct.findById(id);
        if(maybeProduct.isEmpty()){
            return ResponseEntity.badRequest().body("Product not found!");
        }
        Product product = maybeProduct.get();
        User productUser = product.getUser();
        if (!productUser.equals(user)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ValidationError("productId", "This product does not belong to you!"));
        }
        Set<String> links = emulatedUploader.send(formImages);
        product.addImages(links);
        repositoryProduct.save(product);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id") Long id){

        Optional<Product> maybeProduct = repositoryProduct.findById(id);
        if(maybeProduct.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ValidationError("productId", "Product not found!"));
        }
        Product product = maybeProduct.get();
        DetailProductDTO detailProductDTO = DetailProductDTO.from(product, repositoryQuestion, repositoryOpinion);
        return ResponseEntity.ok().body(detailProductDTO);
    }

}
