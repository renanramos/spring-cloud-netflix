package br.com.renanrramossi.auth.infra.delegate;

import br.com.renanrramossi.auth.core.domain.User;

public interface UserDelegate {

  User loadUserByUsername(final String username);
}
