package br.com.zup.mercadolivre.purchase.payment.service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/fake")
public class FakeControllerCalls {

    @PostMapping(value = "/nf")
    public void createNF(@RequestBody FormNotaFiscal formNotaFiscal){
        System.out.println(formNotaFiscal);
    }

    @PostMapping(value = "/ranking")
    public void createNF(@RequestBody FormRanking formRanking){
        System.out.println(formRanking);
    }
}
