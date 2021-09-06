package br.com.zup.mercadolivre.purchase;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryPurchase extends JpaRepository<Purchase, Long> {
}
