package com.bhanu.sis.auth.security;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.time.Instant;
import java.util.*;
public class JwtService {
  private final Key key;
  private final long ttlSeconds;
  public JwtService(String secret, long ttlSeconds){
    this.key = Keys.hmacShaKeyFor(secret.getBytes());
    this.ttlSeconds = ttlSeconds;
  }
  public String generate(String username, Collection<String> roles){
    Instant now = Instant.now();
    return Jwts.builder()
      .setSubject(username)
      .claim("roles", roles)
      .setIssuedAt(Date.from(now))
      .setExpiration(Date.from(now.plusSeconds(ttlSeconds)))
      .signWith(key, SignatureAlgorithm.HS256)
      .compact();
  }
}
