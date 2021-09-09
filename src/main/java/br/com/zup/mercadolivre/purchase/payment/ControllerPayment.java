package br.com.zup.mercadolivre.purchase.payment;

import br.com.zup.mercadolivre.purchase.Purchase;
import br.com.zup.mercadolivre.purchase.RepositoryPurchase;
import br.com.zup.mercadolivre.purchase.Status;
import br.com.zup.mercadolivre.purchase.payment.pagseguro.FormPagSeguroPayment;
import br.com.zup.mercadolivre.purchase.payment.paypal.FormPaypalPayment;
import br.com.zup.mercadolivre.purchase.payment.service.ServicePayment;
import br.com.zup.mercadolivre.shared.config.validation.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/payments")
public class ControllerPayment {

    private RepositoryPayment repositoryPayment;
    private RepositoryPurchase repositoryPurchase;
    private ServicePayment servicePayment;

    public ControllerPayment(RepositoryPayment repositoryPayment, RepositoryPurchase repositoryPurchase, ServicePayment servicePayment) {
        this.repositoryPayment = repositoryPayment;
        this.repositoryPurchase = repositoryPurchase;
        this.servicePayment = servicePayment;
    }

    @PostMapping(value = "/paypal/{id}")
    public ResponseEntity<?> proccessPaypalPayment(@PathVariable("id") Long id, @RequestBody @Valid FormPaypalPayment formPaypalPayment){

        return payment(id, formPaypalPayment);
    }

    @PostMapping(value = "/pagseguro/{id}")
    public ResponseEntity<?> proccessPagseguroPayment(@PathVariable("id") Long id, @RequestBody @Valid FormPagSeguroPayment formPagSeguroPayment){

        return payment(id, formPagSeguroPayment);
    }

    private ResponseEntity<?> payment(Long id, FormPayment formPayment){
        Optional<Purchase> maybePurchase = repositoryPurchase.findById(id);
        if(maybePurchase.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ValidationError("id",
                            "Compra não encontrada!"));
        }

        Purchase purchase = maybePurchase.get();
        Payment payment = formPayment.toModel(purchase);


        Optional<Payment> maybePayment = repositoryPayment.findByIdGatewayAndGateway(payment.getIdGateway(), payment.getGateway());
        if((maybePayment.isPresent() && maybePayment.get().wasSuccessful()) || payment.getPurchase().getStatus() == Status.PAGAMENTO_REALIZADO){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ValidationError("Payment",
                            "Esse pagamento já foi finalizado com sucesso."));
        }
        repositoryPayment.save(payment);
        servicePayment.proccessPayment(payment);
        return ResponseEntity.ok().body(payment.getStatus());
    }
}
