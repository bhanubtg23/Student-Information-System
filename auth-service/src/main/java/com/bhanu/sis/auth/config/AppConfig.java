package com.bhanu.sis.auth.config;
import com.bhanu.sis.auth.security.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class AppConfig {
  @Bean public JwtService jwtService(AuthProps props){ return new JwtService(props.secret(), props.ttlSeconds()); }
}
