package br.com.renanrramossi.auth.infra.config.jwt;

import static br.com.renanrramossi.auth.infra.config.jwt.JwtUtils.resolveToken;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {

  private final JwtTokenProvider jwtTokenProvider;

  @Override
  public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain)
      throws IOException, ServletException {
    final String token = resolveToken((HttpServletRequest) servletRequest);

    setAuthentication(token);

    filterChain.doFilter(servletRequest, servletResponse);
  }

  private void setAuthentication(final String token) {
    if (!jwtTokenProvider.isTokenExpired(token)) {

      final Authentication authentication = jwtTokenProvider.getAuthentication(token);

      if (authentication != null) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }
  }
}
