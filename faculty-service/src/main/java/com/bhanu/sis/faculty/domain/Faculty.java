package com.bhanu.sis.faculty.domain;
import jakarta.persistence.*;
@Entity @Table(name="faculty")
public class Faculty {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
  @Column(nullable=false) public String name;
  @Column(nullable=false, unique=true) public String email;
  @Column(nullable=false) public String department;
}
