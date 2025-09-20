package com.bhanu.sis.faculty.repo;
import com.bhanu.sis.faculty.domain.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
public interface FacultyRepo extends JpaRepository<Faculty, Long> {}
