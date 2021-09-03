package br.com.zup.mercadolivre.product.question;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryQuestion extends JpaRepository<Question, Long> {

    List<Question> findByProduct_id(Long id);
}
