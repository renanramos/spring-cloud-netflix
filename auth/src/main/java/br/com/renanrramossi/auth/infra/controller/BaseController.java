package br.com.renanrramossi.auth.infra.controller;

import br.com.renanrramossi.auth.core.domain.User;
import br.com.renanrramossi.auth.infra.config.jwt.JwtTokenProvider;
import br.com.renanrramossi.auth.infra.controller.entities.UserForm;
import br.com.renanrramossi.auth.infra.delegate.UserDelegate;
import br.com.renanrramossi.auth.interfaceadapter.dto.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
public abstract class BaseController {

  public static final String BEARER = "Bearer ";

  private final UserDelegate userDelegate;

  private final JwtTokenProvider jwtTokenProvider;

  private final BCryptPasswordEncoder passwordEncoder;

  protected BaseController(UserDelegate userDelegate, JwtTokenProvider jwtTokenProvider, BCryptPasswordEncoder passwordEncoder) {
    this.userDelegate = userDelegate;
    this.jwtTokenProvider = jwtTokenProvider;
    this.passwordEncoder = passwordEncoder;
  }

  public LoginResponse buildResponse(final UserForm userForm) {
    var username = userForm.getUsername();

    final User user = getUser(username, userForm.getPassword());

    setAuthenticationOnSecurityContext(user);

    return LoginResponse.builder()
        .username(username)
        .token(getToken(username, user))
        .build();
  }

  private User getUser(final String username, final String password) {
    var user = userDelegate.loadUserByUsername(username);

    if (user == null) {
      log.info("User name not found");
      throw new UsernameNotFoundException("User name not found");
    }

    if (!passwordEncoder.matches(password, user.getPassword())) {
      log.error("Unauthorized");
      throw new BadCredentialsException("Unauthorized");
    }

    return user;
  }

  private void setAuthenticationOnSecurityContext(final User user) {
    final Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());

    SecurityContext context = SecurityContextHolder.createEmptyContext();

    context.setAuthentication(authentication);
  }

  private String getToken(final String username,final User user) {
    return BEARER + jwtTokenProvider.generateToken(username, user.getRoles());
  }

}
