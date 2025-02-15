package br.com.zup.mercadolivre.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositoryUser extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);
}
