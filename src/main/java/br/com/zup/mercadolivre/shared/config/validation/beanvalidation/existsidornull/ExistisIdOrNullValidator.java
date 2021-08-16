package br.com.zup.mercadolivre.shared.config.validation.beanvalidation.existsidornull;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistisIdOrNullValidator implements ConstraintValidator<ExistsIdOrNull, Object> {

    private String domainAttribute;
    private Class<?> aClass;

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void initialize(ExistsIdOrNull constraintAnnotation) {
        domainAttribute = constraintAnnotation.fieldName();
        aClass = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if(o==null){
            return true;
        }
        Query query = entityManager.createQuery("select 1 from "+aClass.getName()+" where "+domainAttribute+"=:value");
        query.setParameter("value", o);
        List<?> list = query.getResultList();
        return list.size() == 1;
    }



}
