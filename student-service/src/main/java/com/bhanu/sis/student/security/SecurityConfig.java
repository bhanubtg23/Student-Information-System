package com.bhanu.sis.student.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration @EnableMethodSecurity
public class SecurityConfig {
  private final JwtFilter jwt;
  public SecurityConfig(JwtFilter jwt){ this.jwt = jwt; }
  @Bean SecurityFilterChain sc(HttpSecurity http) throws Exception {
    http.csrf(csrf->csrf.disable());
    http.authorizeHttpRequests(reg->reg
      .requestMatchers("/v3/api-docs/**","/swagger-ui/**").permitAll()
      .anyRequest().authenticated());
    http.addFilterBefore(jwt, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
}
