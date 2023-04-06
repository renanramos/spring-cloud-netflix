package br.com.renanrramossi.auth.infra.config;

import br.com.renanrramossi.auth.core.usecase.UserUseCaseImpl;
import br.com.renanrramossi.auth.infra.delegate.UserDelegate;
import br.com.renanrramossi.auth.infra.delegate.UserDelegateImpl;
import br.com.renanrramossi.auth.interfaceadapter.gateway.UserGatewayImpl;
import br.com.renanrramossi.auth.interfaceadapter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class AuthConfiguration {

  private final UserRepository userRepository;

  @Bean
  public UserDelegate userDelegate() {
    return new UserDelegateImpl(new UserUseCaseImpl(new UserGatewayImpl(userRepository)));
  }

}
