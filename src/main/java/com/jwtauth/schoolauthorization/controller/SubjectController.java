package com.jwtauth.schoolauthorization.controller;
import com.jwtauth.schoolauthorization.dto.SubjectCreationDto;
import com.jwtauth.schoolauthorization.dto.SubjectDto;
import com.jwtauth.schoolauthorization.service.SubjectService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/subjects")

public class SubjectController {

  @Autowired
  SubjectService subjectService;

  @GetMapping()
  @PreAuthorize("hasAnyRole('ROLE_OFFICE_ADMIN', 'ROLE_TEACHER', 'ROLE_STUDENT')")
  public ResponseEntity<List<SubjectDto>> getSubjects() {
    List<SubjectDto> subjectDtoList = this.subjectService.getSubjects();
    return new ResponseEntity<>(subjectDtoList, HttpStatus.OK);
  }

  @PostMapping()
  @PreAuthorize("hasRole('ROLE_OFFICE_ADMIN')")
  public ResponseEntity<SubjectDto> postSubject(@Valid @RequestBody SubjectCreationDto subjectCreationDto) {
    SubjectDto subjectDto = this.subjectService.insertSubject(subjectCreationDto);
    return new ResponseEntity<>(subjectDto, HttpStatus.CREATED);
  }

}
