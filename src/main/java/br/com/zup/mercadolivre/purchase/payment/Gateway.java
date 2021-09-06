package br.com.zup.mercadolivre.purchase.payment;

public enum Gateway {
    PAYPAL("http://paypal.com?buyerId="), PAGSEGURO("http://pagseguro.com?returnId=");

    private String url;

    Gateway(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
