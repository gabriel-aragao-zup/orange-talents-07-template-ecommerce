package br.com.zup.mercadolivre.purchase.payment;

import br.com.zup.mercadolivre.purchase.Purchase;

public interface FormPayment {
    Payment toModel(Purchase purchase);
}
