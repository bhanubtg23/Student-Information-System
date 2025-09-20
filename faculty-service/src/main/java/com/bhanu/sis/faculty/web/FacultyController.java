package com.bhanu.sis.faculty.web;
import com.bhanu.sis.faculty.domain.Faculty;
import com.bhanu.sis.faculty.repo.FacultyRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/faculty")
public class FacultyController {
  private final FacultyRepo repo;
  public FacultyController(FacultyRepo repo){ this.repo=repo; }
  @GetMapping @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_FACULTY')")
  public Page<Faculty> list(@RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="10") int size){
    return repo.findAll(PageRequest.of(page,size));
  }
  @PostMapping @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public Faculty create(@RequestBody Faculty f){ return repo.save(f); }
}
