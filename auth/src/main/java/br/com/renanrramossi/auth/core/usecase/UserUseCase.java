package br.com.renanrramossi.auth.core.usecase;

import br.com.renanrramossi.auth.core.domain.User;

public interface UserUseCase {

  User loadUserByUsername(final String username);
}
