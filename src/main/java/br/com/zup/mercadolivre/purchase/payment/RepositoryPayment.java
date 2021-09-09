package br.com.zup.mercadolivre.purchase.payment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositoryPayment extends JpaRepository<Payment, Long> {
    Optional<Payment> findByIdGatewayAndGateway(Long idGateway, Gateway gateway);
}
