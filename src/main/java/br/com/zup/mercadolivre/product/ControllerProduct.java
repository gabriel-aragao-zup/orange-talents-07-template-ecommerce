package br.com.zup.mercadolivre.product;

import br.com.zup.mercadolivre.categoria.RepositoryCategory;
import br.com.zup.mercadolivre.shared.config.validation.beanvalidation.existsid.ExistsId;
import br.com.zup.mercadolivre.user.RepositoryUser;
import br.com.zup.mercadolivre.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
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

    public ControllerProduct(RepositoryProduct repositoryProduct, RepositoryCategory repositoryCategory) {
        this.repositoryProduct = repositoryProduct;
        this.repositoryCategory = repositoryCategory;
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
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("This product does not belong to you!");
        }

        Set<String> links = emulatedUploader.send(formImages);
        product.addImages(links);
        repositoryProduct.save(product);

        return ResponseEntity.ok().build();
    }


}
