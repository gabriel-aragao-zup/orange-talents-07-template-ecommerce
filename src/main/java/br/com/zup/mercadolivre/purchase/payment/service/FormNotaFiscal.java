package br.com.zup.mercadolivre.purchase.payment.service;

import com.fasterxml.jackson.annotation.JsonCreator;

public class FormNotaFiscal {

    private Long idPurchase;
    private Long idBuyer;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public FormNotaFiscal(Long idPurchase, Long idBuyer) {
        this.idPurchase = idPurchase;
        this.idBuyer = idBuyer;
    }

    public Long getIdPurchase() {
        return idPurchase;
    }

    public Long getIdBuyer() {
        return idBuyer;
    }

    @Override
    public String toString() {
        return "FormNotaFiscal{" +
                "idPurchase=" + idPurchase +
                ", idBuyer=" + idBuyer +
                '}';
    }
}
