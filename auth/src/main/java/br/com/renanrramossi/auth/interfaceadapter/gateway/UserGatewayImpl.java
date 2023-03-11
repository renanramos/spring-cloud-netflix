package br.com.renanrramossi.auth.interfaceadapter.gateway;

import br.com.renanrramossi.auth.core.domain.User;
import br.com.renanrramossi.auth.core.usecase.gateway.UserGateway;
import br.com.renanrramossi.auth.interfaceadapter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class UserGatewayImpl implements UserGateway {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    var user = userRepository.findByUserName(username);
    verifyUser(username, user);
    return user;
  }

  private void verifyUser(final String username, final User user) {
    if (user == null) {
      throw new UsernameNotFoundException(String.format("Username %s not found.", username));
    }
  }
}
