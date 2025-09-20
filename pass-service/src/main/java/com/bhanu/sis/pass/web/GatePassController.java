package com.bhanu.sis.pass.web;
import com.bhanu.sis.pass.domain.GatePass;
import com.bhanu.sis.pass.repo.GatePassRepo;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@RestController @RequestMapping("/passes")
public class GatePassController {
  private final GatePassRepo repo;
  public GatePassController(GatePassRepo repo){ this.repo=repo; }

  @GetMapping("/health") public Map<String,String> health(){ return Map.of("status","ok"); }

  @PostMapping @PreAuthorize("hasAuthority('ROLE_STUDENT')")
  public GatePass create(@RequestBody Map<String,Object> body){
    GatePass gp=new GatePass();
    gp.studentId=Long.valueOf(body.get("studentId").toString());
    gp.reason=body.get("reason").toString();
    return repo.save(gp);
  }

  @PostMapping("/{id}/decision") @PreAuthorize("hasAuthority('ROLE_FACULTY')")
  @CircuitBreaker(name="db", fallbackMethod="fallbackDecision")
  public GatePass decide(@PathVariable Long id, @RequestParam String decision, Authentication auth){
    GatePass gp=repo.findById(id).orElseThrow();
    if(!List.of("APPROVED","REJECTED").contains(decision)) throw new IllegalArgumentException("decision must be APPROVED or REJECTED");
    gp.status=decision; gp.decidedAt=Instant.now(); gp.decidedBy=auth.getName();
    return repo.save(gp);
  }
  public GatePass fallbackDecision(Long id, String decision, Authentication auth, Throwable t){
    GatePass gp=new GatePass(); gp.id=id; gp.status="PENDING"; gp.reason="FALLBACK"; return gp;
  }

  @GetMapping("/mine/{studentId}") @PreAuthorize("hasAnyAuthority('ROLE_STUDENT','ROLE_FACULTY')")
  public List<GatePass> mine(@PathVariable Long studentId){ return repo.findByStudentId(studentId); }
}
