package br.com.zup.mercadolivre.shared.config.security;

import br.com.zup.mercadolivre.user.RepositoryUser;
import br.com.zup.mercadolivre.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private RepositoryUser repositoryUser;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> user = repositoryUser.findByLogin(login);
        if(user.isPresent()){
            return user.get();
        }
        throw new UsernameNotFoundException("Invalid data");
    }
}
