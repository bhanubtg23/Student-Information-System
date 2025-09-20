package com.bhanu.sis.student.security;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
@Configuration @ConfigurationProperties(prefix="security.jwt")
public class JwtProps {
  private String secret = "dev-local-secret";
  public String secret(){ return secret; }
  public void setSecret(String s){ this.secret = s; }
}
