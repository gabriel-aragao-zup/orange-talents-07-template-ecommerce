package br.com.zup.mercadolivre.product.opinion;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryOpinion extends JpaRepository<Opinion, Long> {

    List<Opinion> findByProduct_id(Long id);
}
