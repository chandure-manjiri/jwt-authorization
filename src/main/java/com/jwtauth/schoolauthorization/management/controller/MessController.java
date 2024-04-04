package com.jwtauth.schoolauthorization.management.controller;

import com.jwtauth.schoolauthorization.exception.ResourceNotFoundException;
import com.jwtauth.schoolauthorization.management.dto.MessCreationDto;
import com.jwtauth.schoolauthorization.management.dto.MessDto;
import com.jwtauth.schoolauthorization.management.dto.MessOwnersDto;
import com.jwtauth.schoolauthorization.management.service.MessService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
@RolesAllowed({"ROLE_STUDENT", "ROLE_MESS_OWNER"})
public class MessController {

  @Autowired
  MessService messService;

  @GetMapping("/mess")
  public ResponseEntity<List<MessDto>> getmess() {
    List<MessDto> mess = this.messService.getAllMess();
    return ResponseEntity.ok(mess);
  }

  @PostMapping("/mess")
  public ResponseEntity<MessDto> createMess(@Valid @RequestBody MessCreationDto messCreationDto) {
    MessDto messDto = this.messService.createMess(messCreationDto);
    return new ResponseEntity<>(messDto, HttpStatus.CREATED);
  }

  @GetMapping("/mess/{mess_id}/owners")
  public ResponseEntity<MessOwnersDto> getAllOwners(@PathVariable("mess_id") Integer mess_id) throws ResourceNotFoundException {
    MessOwnersDto messOwnersDto = this.messService.getAllOwners(mess_id);
    return ResponseEntity.ok(messOwnersDto);
  }
}