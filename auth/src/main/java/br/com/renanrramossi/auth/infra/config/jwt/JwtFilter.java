package br.com.renanrramossi.auth.infra.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.UnsupportedJwtException;
import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.util.Collections;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
public class JwtFilter extends OncePerRequestFilter {

  private final String HEADER = "Authorization";
  private final String PREFIX = "Bearer ";

  private final String secretkey = "chave_microservices";

  @Override
  protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain)
      throws IOException, ServletException {

    try {
      if (checkJwtToken(request)) {
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
    final String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");

    return Jwts.parser()
        .setSigningKey(secretkey.getBytes())
        .parseClaimsJws(jwtToken)
        .getBody();
  }

  private boolean checkJwtToken(HttpServletRequest request) {
    final String authenticationHeader = request.getHeader(HEADER);
    if (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX))
      return false;
    return true;
  }
}
