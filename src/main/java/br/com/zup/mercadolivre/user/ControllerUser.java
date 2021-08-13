package br.com.zup.mercadolivre.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/users")
public class ControllerUser {

    private RepositoryUser repositoryUser;

    public ControllerUser(RepositoryUser repositoryUser) {
        this.repositoryUser = repositoryUser;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid FormUser formUser){
        User user = formUser.toModel();

        repositoryUser.save(user);

        return ResponseEntity.ok().build();
    }
}
