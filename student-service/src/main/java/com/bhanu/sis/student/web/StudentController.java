package com.bhanu.sis.student.web;
import com.bhanu.sis.student.domain.Student;
import com.bhanu.sis.student.repo.StudentRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/students")
public class StudentController {
  private final StudentRepo repo;
  public StudentController(StudentRepo repo){ this.repo = repo; }
  @GetMapping @PreAuthorize("hasAnyAuthority('ROLE_FACULTY','ROLE_ADMIN')")
  public Page<Student> list(@RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="10") int size){
    return repo.findAll(PageRequest.of(page,size));
  }
  @PostMapping @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public Student create(@RequestBody Student s){ return repo.save(s); }
}
