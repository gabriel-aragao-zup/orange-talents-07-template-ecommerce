package br.com.zup.mercadolivre.purchase.payment.paypal;

import br.com.zup.mercadolivre.purchase.Purchase;
import br.com.zup.mercadolivre.purchase.payment.FormPayment;
import br.com.zup.mercadolivre.purchase.payment.Gateway;
import br.com.zup.mercadolivre.purchase.payment.Payment;
import br.com.zup.mercadolivre.purchase.payment.StatusPayment;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class FormPaypalPayment implements FormPayment {

    @NotNull
    private Long id;
    @NotNull
    @Min(0) @Max(1)
    private int status;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public FormPaypalPayment(Long id, int status) {
        this.id = id;
        this.status = status;
    }

    @Override
    public Payment toModel(Purchase purchase) {
        StatusPayment statusPayment;
        if(status == 1){
            statusPayment = StatusPayment.SUCCESS;
        } else {
            statusPayment = StatusPayment.FAIL;
        }
        return new Payment(id, statusPayment, purchase, Gateway.PAYPAL);
    }
}
