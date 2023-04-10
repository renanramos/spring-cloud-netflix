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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
public class UserController {

  private final UserDelegate userDelegate;

  private final JwtTokenProvider jwtTokenProvider;

  @GetMapping("/teste")
  public ResponseEntity<String> teste() {
    return ResponseEntity.ok("teste");
  }


  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@RequestParam("username") String username, @RequestParam("password") String password) {
    log.info("passou controller");
    var user = userDelegate.loadUserByUsername(username);

    var token = "";

    if (user == null) {
      throw new UsernameNotFoundException("User name not found");
    }

    token = "Bearer " + jwtTokenProvider.generateToken(username, user.getRoles());

    return ResponseEntity.ok(LoginResponse.builder()
            .token(token)
            .username(username)
        .build());
  }
}
