package com.bhanu.sis.pass.domain;
import jakarta.persistence.*;
import java.time.Instant;
@Entity @Table(name="gate_passes")
public class GatePass {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
  @Column(nullable=false) public Long studentId;
  @Column(nullable=false) public String reason;
  @Column(nullable=false) public String status = "PENDING";
  @Column(nullable=false) public Instant createdAt = Instant.now();
  public Instant decidedAt; public String decidedBy;
}
