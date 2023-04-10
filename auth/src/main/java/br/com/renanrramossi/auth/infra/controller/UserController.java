package br.com.renanrramossi.auth.infra.controller;

import br.com.renanrramossi.auth.infra.config.jwt.JwtTokenProvider;
import br.com.renanrramossi.auth.infra.delegate.UserDelegate;
import br.com.renanrramossi.auth.interfaceadapter.dto.LoginResponse;
import br.com.renanrramossi.auth.infra.controller.entities.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController extends BaseController {

  @Autowired
  public UserController(final UserDelegate userDelegate, final JwtTokenProvider jwtTokenProvider,
      final BCryptPasswordEncoder passwordEncoder) {
    super(userDelegate, jwtTokenProvider, passwordEncoder);
  }

  @GetMapping("/teste")
  public ResponseEntity<String> teste() {
    return ResponseEntity.ok("teste");
  }


  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(final @RequestBody UserForm userForm) {
    return ResponseEntity.ok(buildResponse(userForm));
  }
}
