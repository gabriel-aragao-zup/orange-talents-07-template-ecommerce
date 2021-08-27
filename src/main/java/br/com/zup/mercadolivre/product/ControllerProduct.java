package br.com.zup.mercadolivre.product;

import br.com.zup.mercadolivre.categoria.RepositoryCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ControllerProduct {

    private RepositoryProduct repositoryProduct;
    private RepositoryCategory repositoryCategory;
    @Autowired
    private NoDuplicatedFeatureValidator noDuplicatedFeatureValidator;

    public ControllerProduct(RepositoryProduct repositoryProduct, RepositoryCategory repositoryCategory) {
        this.repositoryProduct = repositoryProduct;
        this.repositoryCategory = repositoryCategory;
    }

    @InitBinder
    public void init(WebDataBinder webDataBinder){
        webDataBinder.addValidators(noDuplicatedFeatureValidator);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody @Valid FormProduct formProduct){
        Product product = formProduct.toModel(repositoryCategory);
        repositoryProduct.save(product);
        return ResponseEntity.ok().build();
    }
}
