package br.com.zup.mercadolivre.product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryProduct extends JpaRepository<Product, Long> {
}
