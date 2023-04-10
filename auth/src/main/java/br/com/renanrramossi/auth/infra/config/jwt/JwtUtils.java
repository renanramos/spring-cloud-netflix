package br.com.renanrramossi.auth.infra.config.jwt;

import io.jsonwebtoken.Jwts;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class JwtUtils {

  private static final String PREFIX_BEARER_TOKEN = "Bearer ";

  private JwtUtils() {
  }

  public static String extractUserNameFromToken(final String token, final String secretKey) {
    return Jwts.parser()
        .setSigningKey(secretKey)
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }

  public static String resolveToken(final HttpServletRequest request) {
    final String bearerToken = request.getHeader("Authorization");
    if (isValidToken(bearerToken)) {
      return bearerToken.substring(7);
    }
    return null;
  }

  public static boolean isValidToken(final String bearerToken) {
    if (bearerToken == null || !bearerToken.startsWith(PREFIX_BEARER_TOKEN))
      return false;
    return true;
  }
}
