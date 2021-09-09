package br.com.zup.mercadolivre.purchase.payment.service;

import br.com.zup.mercadolivre.purchase.RepositoryPurchase;
import br.com.zup.mercadolivre.purchase.Status;
import br.com.zup.mercadolivre.purchase.payment.Payment;
import br.com.zup.mercadolivre.shared.config.email.FakeEmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicePayment {

    @Autowired
    private CallsPayment callsPayment;
    @Autowired
    private FakeEmailSender fakeEmailSender;
    @Autowired
    private RepositoryPurchase repositoryPurchase;

    public void proccessPayment(Payment payment){
        if(payment.wasSuccessful()){
            payment.getPurchase().setStatus(Status.PAGAMENTO_REALIZADO);
            repositoryPurchase.save(payment.getPurchase());
            try {
                callsPayment.postNF(new FormNotaFiscal(payment.getPurchase().getId(),
                        payment.getPurchase().getBuyer().getId()));
            }catch (Exception e){ }
            try {
                callsPayment.postRanking(new FormRanking(payment.getPurchase().getId(),
                        payment.getPurchase().getProduct().getUser().getId()));
            }catch (Exception e){ }

            fakeEmailSender.send(new EmailPaymentSuccessfull(payment));
        } else {
            fakeEmailSender.send(new EmailPaymentFail(payment));
        }
    }
}
