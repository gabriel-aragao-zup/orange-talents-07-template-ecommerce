package br.com.zup.mercadolivre.purchase.payment.pagseguro;

import br.com.zup.mercadolivre.purchase.Purchase;
import br.com.zup.mercadolivre.purchase.payment.FormPayment;
import br.com.zup.mercadolivre.purchase.payment.Gateway;
import br.com.zup.mercadolivre.purchase.payment.Payment;
import br.com.zup.mercadolivre.purchase.payment.StatusPayment;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotNull;

public class FormPagSeguroPayment implements FormPayment {

    @NotNull
    private Long id;
    @NotNull
    private StatusPagSeguro status;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public FormPagSeguroPayment(Long id, StatusPagSeguro status) {
        this.id = id;
        this.status = status;
    }

    @Override
    public Payment toModel(Purchase purchase) {
        StatusPayment statusPayment;
        if(status == StatusPagSeguro.SUCESSO){
            statusPayment = StatusPayment.SUCCESS;
        } else {
            statusPayment = StatusPayment.FAIL;
        }
        return new Payment(id, statusPayment, purchase, Gateway.PAGSEGURO);
    }
}
