package com.bhanu.sis.common.dto;
import java.util.List;
public class JwtResponse {
  public String token; public String username; public List<String> roles;
  public JwtResponse(String token, String username, List<String> roles){
    this.token=token; this.username=username; this.roles=roles;
  }
}
