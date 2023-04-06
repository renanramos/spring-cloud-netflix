package br.com.renanrramossi.auth.infra.controller;

import br.com.renanrramossi.auth.core.domain.User;
import br.com.renanrramossi.auth.infra.config.jwt.JwtTokenProvider;
import br.com.renanrramossi.auth.infra.delegate.UserDelegate;
import br.com.renanrramossi.auth.interfaceadapter.dto.UserDTO;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping
@RestController
public class UserController {

  private final UserDelegate userDelegate;

//  private final AuthenticationManager authenticationManager;

  private final JwtTokenProvider jwtTokenProvider;

  @GetMapping("/teste")
  public ResponseEntity<String> teste() {
    return ResponseEntity.ok("teste");
  }


  @PostMapping("/login")
  public ResponseEntity<Map<Object, Object>> login(@RequestBody UserDTO userDTO) {

    var username = userDTO.getUserName();
    var password = userDTO.getPassword();

//    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

    var user = userDelegate.loadUserByUsername(username);

    var token = "";

    if (user == null) {
      throw new UsernameNotFoundException("User name not found");
    }

    token = jwtTokenProvider.generateToken(username, user.getRoles());

    Map<Object, Object> model = new HashMap<>();
    model.put("username", username);
    model.put("token", token);

    return ResponseEntity.status(HttpStatus.OK).body(model);
  }
}
