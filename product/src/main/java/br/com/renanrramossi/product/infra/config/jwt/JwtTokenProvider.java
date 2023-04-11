package br.com.renanrramossi.product.infra.config.jwt;

import static br.com.renanrramossi.product.infra.config.jwt.JwtUtils.extractUserNameFromToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

  @Value("${security.jwt.token.secret-key}")
  private String secretKey;

//  private final UserDetailsService userDetailsService;

  @PostConstruct
  protected void init() {
    secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
  }

  public Authentication getAuthentication(final String token) {

    final UserDetails userDetails = new UserDetails() {
      public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
      }
      public String getPassword() {
        return "";
      }
      public String getUsername() {
        return "";
      }
      public boolean isAccountNonExpired() {
        return true;
      }
      public boolean isAccountNonLocked() {
        return true;
      }
      public boolean isCredentialsNonExpired() {
        return true;
      }
      public boolean isEnabled() {
        return true;
      }
    };

//        this.userDetailsService.loadUserByUsername(extractUserNameFromToken(token, secretKey));

    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

}
