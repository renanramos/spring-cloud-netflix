package br.com.renanrramossi.auth.infra.delegate;

import br.com.renanrramossi.auth.core.domain.User;
import br.com.renanrramossi.auth.core.usecase.UserUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserDelegateImpl implements UserDelegate{

  private final UserUseCase userUseCase;

  @Override
  public User loadUserByUsername(final String username) {
    return userUseCase.loadUserByUsername(username);
  }
}
