package br.com.zup.mercadolivre.shared.config.security.token;

import br.com.zup.mercadolivre.user.RepositoryUser;
import br.com.zup.mercadolivre.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class TokenFilterAuthentication  extends OncePerRequestFilter {


    private String secret;

    private RepositoryUser repositoryUser;

    public TokenFilterAuthentication(RepositoryUser repositoryUser, String secret) {
        this.repositoryUser = repositoryUser;
        this.secret = secret;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String token = getToken(httpServletRequest);
        boolean isValid = tokenValidate(token);
        if(isValid){
            authenticate(token);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void authenticate(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        Long idUsuario = Long.parseLong(claims.getSubject());
        User user = repositoryUser.findById(idUsuario).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private boolean tokenValidate(String token) {
        try{
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    private String getToken(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        if(token == null || token.isBlank() || !token.startsWith("Bearer ")){
            return null;
        }
        return token.substring(7, token.length());
    }
}
