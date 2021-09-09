package br.com.zup.mercadolivre.purchase.payment.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "paymentcalls", url = "http://localhost:8080/api/v1/fake")
public interface CallsPayment {

    @PostMapping(value = "/nf")
    Void postNF(FormNotaFiscal formNotaFiscal);

    @PostMapping(value = "/ranking")
    Void postRanking(FormRanking formRanking);
}
