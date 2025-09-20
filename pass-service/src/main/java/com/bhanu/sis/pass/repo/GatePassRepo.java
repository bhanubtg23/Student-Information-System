package com.bhanu.sis.pass.repo;
import com.bhanu.sis.pass.domain.GatePass;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface GatePassRepo extends JpaRepository<GatePass, Long> {
  List<GatePass> findByStudentId(Long studentId);
}
