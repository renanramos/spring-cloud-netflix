package br.com.renanrramossi.auth.infra.config;

import br.com.renanrramossi.auth.infra.config.jwt.JwtConfigurer;
import br.com.renanrramossi.auth.infra.config.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

  private final JwtTokenProvider jwtTokenProvider;

  @Bean
  public SecurityFilterChain filterChain(final HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
        .httpBasic().disable()
        .csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests((auths) -> auths
            .antMatchers("/auth/login").permitAll()
//            .antMatchers("/auth/**").authenticated()
            .anyRequest().authenticated()
        )
        .exceptionHandling(e -> e.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
//        .build();
////        .authorizeHttpRequests((auths) -> auths
////            .antMatchers("/auth/login").permitAll()
//////            .anyRequest().authenticated()
////        )
//        .apply(new JwtConfigurer(jwtTokenProvider))
//        .and()
        .build();
  }
}
