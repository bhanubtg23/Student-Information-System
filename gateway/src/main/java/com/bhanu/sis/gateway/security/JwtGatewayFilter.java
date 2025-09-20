package com.bhanu.sis.gateway.security;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.security.Key;

@Configuration
public class JwtGatewayFilter {
  @Value("${security.jwt.secret}") private String secret;

  private boolean open(String path){
    return path.startsWith("/auth/") || path.startsWith("/v3/api-docs") || path.startsWith("/swagger-ui");
  }

  @Bean
  GlobalFilter authFilter(){
    return (exchange, chain) -> {
      var path = exchange.getRequest().getURI().getPath();
      if (open(path)) return chain.filter(exchange);
      var auth = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
      if (auth == null || !auth.startsWith("Bearer ")) return deny(exchange);
      try{
        Key key = Keys.hmacShaKeyFor(secret.getBytes());
        Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(auth.substring(7));
        return chain.filter(exchange);
      } catch(Exception e){
        return deny(exchange);
      }
    };
  }

  private Mono<Void> deny(ServerWebExchange ex){
    ex.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
    return ex.getResponse().setComplete();
  }
}
