package br.com.zup.mercadolivre.purchase;

import br.com.zup.mercadolivre.product.Product;
import br.com.zup.mercadolivre.product.RepositoryProduct;
import br.com.zup.mercadolivre.purchase.payment.Gateway;
import br.com.zup.mercadolivre.shared.config.validation.beanvalidation.existsid.ExistsId;
import br.com.zup.mercadolivre.user.User;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class FormPurchase {

    @NotNull
    @ExistsId(domainClass = Product.class, fieldName = "id")
    private Long productId;
    @NotNull
    @Positive
    private Integer quantity;
    @NotNull
    private Gateway gateway;


    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public FormPurchase(Long productId, Integer quantity, Gateway gateway) {
        this.productId = productId;
        this.quantity = quantity;
        this.gateway = gateway;
    }

    public Purchase toModel(User buyer, Product product){
        return new Purchase(product, quantity, buyer, gateway);
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Gateway getGateway() {
        return gateway;
    }
}
