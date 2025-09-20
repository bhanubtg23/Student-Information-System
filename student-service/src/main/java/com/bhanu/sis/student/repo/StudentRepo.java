package com.bhanu.sis.student.repo;
import com.bhanu.sis.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
public interface StudentRepo extends JpaRepository<Student, Long> {}
