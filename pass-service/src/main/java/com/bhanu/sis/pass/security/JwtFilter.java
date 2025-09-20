package com.bhanu.sis.pass.security;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.security.Key;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtFilter extends OncePerRequestFilter {
  private final JwtProps props;
  public JwtFilter(JwtProps props){ this.props = props; }
  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain){
    try{
      String a = req.getHeader("Authorization");
      if(a != null && a.startsWith("Bearer ")){
        Key key = Keys.hmacShaKeyFor(props.secret().getBytes());
        var jws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(a.substring(7));
        var roles = (List<String>) jws.getBody().get("roles");
        var auth = new UsernamePasswordAuthenticationToken(jws.getBody().getSubject(), null,
            roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        SecurityContextHolder.getContext().setAuthentication(auth);
      }
      chain.doFilter(req,res);
    } catch(Exception e){
      try{ res.setStatus(401); res.setContentType(MediaType.APPLICATION_JSON_VALUE); res.getWriter().write("{"error":"unauthorized"}"); } catch(Exception ignore){}
    }
  }
}
