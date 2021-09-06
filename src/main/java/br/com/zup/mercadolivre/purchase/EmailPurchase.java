package br.com.zup.mercadolivre.purchase;

import br.com.zup.mercadolivre.shared.config.email.Email;

public class EmailPurchase implements Email {

    private String from;
    private String to;
    private String title;
    private String subject;


    public EmailPurchase(Purchase purchase) {
        this.from = purchase.getBuyer().getEmail();
        this.to = purchase.getProduct().getUser().getEmail();
        this.title = "Purchase made on item "+ purchase.getProduct();
        this.subject = "The User " + purchase.getBuyer().getUsername() +
                " purchased " + purchase.getQuantity().toString() +
                " unities of the product " + purchase.getProduct().getName();
    }

    @Override
    public String getFrom() {
        return this.from;
    }

    @Override
    public String getTo() {
        return this.to;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getSubject() {
        return this.subject;
    }
}
