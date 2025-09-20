package com.bhanu.sis.auth.web;
import com.bhanu.sis.auth.domain.User;
import com.bhanu.sis.auth.repo.UserRepo;
import com.bhanu.sis.auth.security.JwtService;
import com.bhanu.sis.common.dto.LoginRequest;
import com.bhanu.sis.common.dto.JwtResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController @RequestMapping("/auth")
public class AuthController {
  private final UserRepo users;
  private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
  private final JwtService jwt;
  public AuthController(UserRepo users, JwtService jwt){ this.users = users; this.jwt = jwt; }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody Map<String,Object> body){
    String username = (String) body.get("username");
    String password = (String) body.get("password");
    List<String> roles = (List<String>) body.getOrDefault("roles", List.of("ROLE_STUDENT"));
    if (users.existsByUsername(username)) return ResponseEntity.badRequest().body(Map.of("message","username taken"));
    User u = new User(); u.username=username; u.passwordHash=encoder.encode(password); u.roles=Set.copyOf(roles);
    users.save(u);
    return ResponseEntity.ok(Map.of("status","created"));
  }

  @PostMapping("/login")
  public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest req){
    User u = users.findByUsername(req.username).orElseThrow();
    if(!encoder.matches(req.password, u.passwordHash)) throw new RuntimeException("bad credentials");
    String token = jwt.generate(u.username, u.roles);
    return ResponseEntity.ok(new JwtResponse(token, u.username, List.copyOf(u.roles)));
  }
}
