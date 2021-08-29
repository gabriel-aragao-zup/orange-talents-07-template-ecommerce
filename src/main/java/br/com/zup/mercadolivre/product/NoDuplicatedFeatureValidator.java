package br.com.zup.mercadolivre.product;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.HashSet;
import java.util.Set;

@Component
public class NoDuplicatedFeatureValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return FormProduct.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if(errors.hasErrors()){
            return;
        }
        FormProduct formProduct = (FormProduct) o;
        Set<Feature> featureSet = new HashSet<>(formProduct.getFeatures());
        if(featureSet.size() != formProduct.getFeatures().size()){
            errors.rejectValue("Features", null, null, "Defina caracteristicas com chaves diferentes");
        }


    }
}
