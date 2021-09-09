package br.com.zup.mercadolivre.purchase.payment.service;

import br.com.zup.mercadolivre.purchase.payment.Payment;
import br.com.zup.mercadolivre.shared.config.email.Email;

public class EmailPaymentSuccessfull implements Email {

    private String from;
    private String to;
    private String title;
    private String subject;

    public EmailPaymentSuccessfull(Payment payment) {
        from = payment.getPurchase().getProduct().getUser().getEmail();
        to = payment.getPurchase().getBuyer().getEmail();
        title = "Pagamento efetuado com sucesso.";
        subject = "Sua compra do produto " +
                payment.getPurchase().getProduct().getName() +
                " teve o pagamento efetuado com sucesso.";
    }

    @Override
    public String getFrom() {
        return null;
    }

    @Override
    public String getTo() {
        return null;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getSubject() {
        return null;
    }
}
