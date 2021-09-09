package br.com.zup.mercadolivre.purchase.payment;

import br.com.zup.mercadolivre.purchase.Purchase;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private Long idGateway;
    @Enumerated(EnumType.STRING)
    private StatusPayment status;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @ManyToOne
    private Purchase purchase;

    @Enumerated(EnumType.STRING)
    private Gateway gateway;

    @Deprecated
    public Payment() {
    }

    public Payment(Long idGateway, StatusPayment status, Purchase purchase, Gateway gateway) {
        this.idGateway = idGateway;
        this.status = status;
        this.purchase = purchase;
        this.gateway = gateway;
    }

    public boolean wasSuccessful(){
        return status == StatusPayment.SUCCESS;
    }

    public Long getIdGateway() {
        return idGateway;
    }

    public Gateway getGateway() {
        return gateway;
    }

    public StatusPayment getStatus() {
        return status;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setStatus(StatusPayment status) {
        if(this.status != StatusPayment.SUCCESS){
            this.status = status;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return idGateway.equals(payment.idGateway) && gateway == payment.gateway;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGateway, gateway);
    }
}
