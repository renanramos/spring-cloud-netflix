package br.com.renanrramossi.auth.infra.config.jwt;

import static br.com.renanrramossi.auth.infra.config.jwt.JwtUtils.HEADER;
import static br.com.renanrramossi.auth.infra.config.jwt.JwtUtils.isValidToken;
import static br.com.renanrramossi.auth.infra.config.jwt.JwtUtils.resolveToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.UnsupportedJwtException;
import java.io.IOException;
import java.util.Collections;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
@EnableConfigurationProperties()
public class JwtFilter extends OncePerRequestFilter {

  @Value("${security.jwt.token.secret-key}")
  private final String SECRET_KEY = "chave_microservices";

  @Override
  protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain)
      throws IOException, ServletException {

    final String token = request.getHeader(HEADER);

    try {
      if (isValidToken(token)) {
        final Claims claims = validateToken(request);
        setUpSpringAuthentication(claims);
      } else {
        SecurityContextHolder.clearContext();
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      }

      filterChain.doFilter(request, response);
    } catch (ExpiredJwtException | UnsupportedJwtException exception) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exception.getMessage());
    }
  }

  private void setUpSpringAuthentication(Claims claims) {

    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
        Collections.emptyList());
    SecurityContextHolder.getContext().setAuthentication(auth);
  }

  private Claims validateToken(HttpServletRequest request) {
    final String jwtToken = resolveToken(request);
    return Jwts.parser()
        .setSigningKey(SECRET_KEY.getBytes())
        .parseClaimsJws(jwtToken)
        .getBody();
  }

}
