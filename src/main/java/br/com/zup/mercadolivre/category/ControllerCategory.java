package br.com.zup.mercadolivre.category;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/categories")
public class ControllerCategory {

    private RepositoryCategory repositoryCategory;

    public ControllerCategory(RepositoryCategory repositoryCategory) {
        this.repositoryCategory = repositoryCategory;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid FormCategoy formCategoy){
        Category category = formCategoy.toModel(repositoryCategory);
        repositoryCategory.save(category);
        return ResponseEntity.ok().build();
    }
}
