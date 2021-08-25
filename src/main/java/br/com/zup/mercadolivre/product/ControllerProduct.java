package br.com.zup.mercadolivre.product;

import br.com.zup.mercadolivre.categoria.RepositoryCategory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ControllerProduct {

    private RepositoryProduct repositoryProduct;
    private RepositoryCategory repositoryCategory;

    public ControllerProduct(RepositoryProduct repositoryProduct, RepositoryCategory repositoryCategory) {
        this.repositoryProduct = repositoryProduct;
        this.repositoryCategory = repositoryCategory;
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody @Valid FormProduct formProduct){
        Product product = formProduct.toModel(repositoryCategory);
        repositoryProduct.save(product);
        return ResponseEntity.ok().build();
    }
}
