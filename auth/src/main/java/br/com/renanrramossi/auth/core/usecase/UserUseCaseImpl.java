package br.com.renanrramossi.auth.core.usecase;

import br.com.renanrramossi.auth.core.domain.User;
import br.com.renanrramossi.auth.core.usecase.gateway.UserGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserUseCaseImpl implements UserUseCase{

  private final UserGateway userGateway;

  @Override
  public User loadUserByUsername(final String username) {
    return userGateway.loadUserByUsername(username);
  }
}
