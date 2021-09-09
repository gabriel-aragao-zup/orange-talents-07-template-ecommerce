package br.com.zup.mercadolivre.purchase;

import br.com.zup.mercadolivre.product.Product;
import br.com.zup.mercadolivre.product.RepositoryProduct;
import br.com.zup.mercadolivre.product.question.EmailQuestion;
import br.com.zup.mercadolivre.shared.config.email.FakeEmailSender;
import br.com.zup.mercadolivre.shared.config.validation.ValidationError;
import br.com.zup.mercadolivre.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/purchases")
public class ControllerPurchase {

    private RepositoryProduct repositoryProduct;
    private RepositoryPurchase repositoryPurchase;
    private UriComponentsBuilder uriComponentsBuilder;

    @Autowired
    private FakeEmailSender fakeEmailSender;

    public ControllerPurchase(RepositoryProduct repositoryProduct, RepositoryPurchase repositoryPurchase) {
        this.repositoryProduct = repositoryProduct;
        this.repositoryPurchase = repositoryPurchase;
    }

    @PostMapping
    public ResponseEntity<?> purchaseItem(@RequestBody @Valid FormPurchase formPurchase, @AuthenticationPrincipal User buyer){
        Product product = repositoryProduct.findById(formPurchase.getProductId()).get();
        Boolean hasStock = product.stockDecrease(formPurchase.getQuantity());
        if(!hasStock){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ValidationError("quantity",
                            "Essa quantidade do produto não está disponível no estoque."));
        }
        repositoryProduct.save(product);
        Purchase purchase = formPurchase.toModel(buyer, product);
        repositoryPurchase.save(purchase);

        try {
            fakeEmailSender.send(new EmailPurchase(purchase));
        } catch (Exception e) {
            e.printStackTrace();
        }

        URI uri = ServletUriComponentsBuilder.fromHttpUrl(formPurchase.getGateway().getUrl())
                .path("{id}&redirectUrl=/payment"+formPurchase.getGateway().getUrlRetorno())
                .buildAndExpand(purchase.getId().toString()).toUri();
        return ResponseEntity.status(HttpStatus.FOUND).location(uri).build();
    }
}
