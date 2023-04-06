package br.com.renanrramossi.auth.core.usecase.gateway;

import br.com.renanrramossi.auth.core.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserGateway extends UserDetailsService {

  User loadUserByUsername(final String username);
}
