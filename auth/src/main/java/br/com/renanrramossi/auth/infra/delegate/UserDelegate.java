package br.com.renanrramossi.auth.infra.delegate;

import br.com.renanrramossi.auth.core.domain.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserDelegate {

  User loadUserByUsername(final String username);
}
