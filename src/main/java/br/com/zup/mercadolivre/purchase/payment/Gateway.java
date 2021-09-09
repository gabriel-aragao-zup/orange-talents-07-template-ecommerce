package br.com.zup.mercadolivre.purchase.payment;

public enum Gateway {
    PAYPAL("http://paypal.com?buyerId=", "/paypal/{id}"), PAGSEGURO("http://pagseguro.com?returnId=", "/pagseguro/{id}");

    private String url;
    private String urlRetorno;

    Gateway(String url, String urlRetorno) {
        this.url = url;
        this.urlRetorno = urlRetorno;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlRetorno() {
        return urlRetorno;
    }
}
