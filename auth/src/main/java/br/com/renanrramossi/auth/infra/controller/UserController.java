package br.com.renanrramossi.auth.infra.controller;

import br.com.renanrramossi.auth.infra.config.jwt.JwtTokenProvider;
import br.com.renanrramossi.auth.infra.delegate.UserDelegate;
import br.com.renanrramossi.auth.interfaceadapter.dto.LoginResponse;
import br.com.renanrramossi.auth.interfaceadapter.dto.UserForm;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
public class UserController {

  private final UserDelegate userDelegate;

  private final JwtTokenProvider jwtTokenProvider;

  private final BCryptPasswordEncoder passwordEncoder;

  @GetMapping("/teste")
  public ResponseEntity<String> teste() {
    return ResponseEntity.ok("teste");
  }


  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@RequestParam("username") String username, @RequestParam("password") String password) {
    var user = userDelegate.loadUserByUsername(username);

    var token = "";

    if (user == null) {
      throw new UsernameNotFoundException("User name not found");
    }

    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new BadCredentialsException("Unauthorized");
    }

    token = "Bearer " + jwtTokenProvider.generateToken(username, user.getRoles());

    final Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());

    SecurityContext context = SecurityContextHolder.createEmptyContext();

    context.setAuthentication(authentication);

    return ResponseEntity.ok(LoginResponse.builder()
            .token(token)
            .username(username)
        .build());
  }
}
