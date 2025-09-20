package com.bhanu.sis.auth.domain;
import jakarta.persistence.*;
import java.util.*;
@Entity @Table(name="users")
public class User {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
  @Column(unique=true, nullable=false) public String username;
  @Column(nullable=false) public String passwordHash;
  @ElementCollection(fetch=FetchType.EAGER)
  @CollectionTable(name="user_roles", joinColumns=@JoinColumn(name="user_id"))
  @Column(name="role")
  public Set<String> roles = new HashSet<>();
}
