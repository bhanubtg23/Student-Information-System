package com.bhanu.sis.student.domain;
import jakarta.persistence.*;
@Entity @Table(name="students")
public class Student {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
  @Column(nullable=false) public String name;
  @Column(nullable=false, unique=true) public String email;
  @Column(nullable=false) public String department;
}
