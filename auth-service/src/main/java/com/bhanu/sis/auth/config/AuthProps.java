package com.bhanu.sis.auth.config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
@Configuration @ConfigurationProperties(prefix="auth.jwt")
public class AuthProps {
  private String secret = "dev-secret-change";
  private long ttlSeconds = 7200;
  public String secret(){ return secret; }
  public long ttlSeconds(){ return ttlSeconds; }
  public void setSecret(String s){ this.secret = s; }
  public void setTtlSeconds(long t){ this.ttlSeconds = t; }
}
