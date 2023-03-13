package br.com.renanrramossi.auth.infra.config.jwt;

import static br.com.renanrramossi.auth.infra.config.jwt.JwtUtils.extractUserNameFromToken;
import static br.com.renanrramossi.auth.infra.config.jwt.JwtUtils.isValidToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@Slf4j
@RequiredArgsConstructor
public class JwtTokenProvider {

  @Value("${security.jwt.token.secret-key}")
  private String secretKey;

  @Value("${security.jwt.token.expire-length}")
  private Long expireLength;

//  @Qualifier("UserService")
//  @Autowired
  private final UserDetailsService userDetailsService;

  @PostConstruct
  protected void init() {
    secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
  }

  public String generateToken(final String username, final List<String> roles) {

    final Claims claims = Jwts.claims().setSubject(username);
    claims.put("roles", roles);

    final Date now = new Date();
    final Date expireIn = new Date(now.getTime() + expireLength);

    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(expireIn)
        .signWith(SignatureAlgorithm.HS256, secretKey)
        .compact();
  }

  public Authentication getAuthentication(final String token) {

    final UserDetails userDetails = this.userDetailsService.loadUserByUsername(extractUserNameFromToken(token, secretKey));

    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  public boolean isTokenExpired(final String token) {

    try {
      final Jws<Claims> claims = Jwts.parser()
          .setSigningKey(secretKey)
          .parseClaimsJws(token);

      return !claims.getBody().getExpiration().before(new Date());
    } catch (JwtException | IllegalArgumentException exception) {
      log.error(exception.getLocalizedMessage());
      return false;
    }
  }


}
