package br.com.zup.mercadolivre.product.question;

import br.com.zup.mercadolivre.product.Product;
import br.com.zup.mercadolivre.shared.config.email.Email;
import br.com.zup.mercadolivre.user.User;

import java.util.Set;

public class EmailQuestion implements Email {

    private String from;
    private String to;
    private String Title;
    private String subject;

    public EmailQuestion(User whoAsks, User productOwner, Product product, Question question) {
        this.from = productOwner.getEmail();
        this.to = whoAsks.getEmail();
        Title = whoAsks.getUsername() + " asked a question about the product " + product.getName();
        this.subject = "At " + question.getCreatedAt() +
                " the user " + whoAsks.getUsername() +
                " asked " + question.getTitle() +
                " about the product " + product.getName();
    }

    @Override
    public String getFrom() {
        return from;
    }

    @Override
    public String getTo() {
        return to;
    }

    @Override
    public String getTitle() {
        return Title;
    }

    @Override
    public String getSubject() {
        return subject;
    }
}
