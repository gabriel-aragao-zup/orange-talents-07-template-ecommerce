package br.com.zup.mercadolivre.purchase;

import br.com.zup.mercadolivre.product.Product;
import br.com.zup.mercadolivre.purchase.payment.Gateway;
import br.com.zup.mercadolivre.user.User;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Product product;
    private Integer quantity;
    @ManyToOne
    private User buyer;
    private BigDecimal value;
    @Enumerated(EnumType.STRING)
    private Gateway gateway;
    @Enumerated(EnumType.STRING)
    private Status status;

    public Purchase(Product product, Integer quantity, User buyer, Gateway gateway) {
        this.product = product;
        this.quantity = quantity;
        this.buyer = buyer;
        this.value = product.getPrice();
        this.gateway = gateway;
        this.status = Status.INICIADA;
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public User getBuyer() {
        return buyer;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
