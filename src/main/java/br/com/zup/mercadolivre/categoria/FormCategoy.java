package br.com.zup.mercadolivre.categoria;

import br.com.zup.mercadolivre.shared.config.validation.beanvalidation.existsidornull.ExistsIdOrNull;
import br.com.zup.mercadolivre.shared.config.validation.beanvalidation.uniquevalue.UniqueValue;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class FormCategoy {

    @NotBlank
    @UniqueValue(domainClass = Category.class, fieldName = "name")
    private String name;

    @ExistsIdOrNull(domainClass = Category.class, fieldName = "id")
    private Long motherCategoryId;

    public Category toModel(RepositoryCategory repositoryCategory){
        Category category = new Category(name);
        if(motherCategoryId != null){
            Optional<Category> maybeCategory = repositoryCategory.findById(motherCategoryId);
            if(maybeCategory.isPresent()){
                Category motherCategory = maybeCategory.get();
                category.setMotherCategory(motherCategory);
            }
        }
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMotherCategoryId(Long motherCategoryId) {
        this.motherCategoryId = motherCategoryId;
    }
}
