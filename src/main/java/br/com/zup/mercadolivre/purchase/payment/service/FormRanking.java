package br.com.zup.mercadolivre.purchase.payment.service;

import com.fasterxml.jackson.annotation.JsonCreator;

public class FormRanking {

    private Long idPurchase;
    private Long idSeller;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public FormRanking(Long idPurchase, Long idSeller) {
        this.idPurchase = idPurchase;
        this.idSeller = idSeller;
    }

    public Long getIdPurchase() {
        return idPurchase;
    }

    public Long getIdSeller() {
        return idSeller;
    }

    @Override
    public String toString() {
        return "FormRanking{" +
                "idPurchase=" + idPurchase +
                ", idSeller=" + idSeller +
                '}';
    }
}
