package br.com.zup.mercadolivre.shared.config.email;

import org.springframework.stereotype.Component;

@Component
public class FakeEmailSender implements EmailSender{
    @Override
    public void send(Email email) {
        System.out.println("Email sent from: ");
        System.out.println(email.getFrom());
        System.out.println("Email sent to: ");
        System.out.println(email.getTo());
        System.out.println("Email title: ");
        System.out.println(email.getTitle());
        System.out.println("Email Subject: ");
        System.out.println(email.getSubject());

    }
}
