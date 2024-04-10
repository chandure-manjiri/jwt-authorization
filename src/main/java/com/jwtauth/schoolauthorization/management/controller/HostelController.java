package com.jwtauth.schoolauthorization.management.controller;
import com.jwtauth.schoolauthorization.management.dto.HostelCreationDto;
import com.jwtauth.schoolauthorization.management.dto.HostelDto;
import com.jwtauth.schoolauthorization.management.service.HostelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;

@RestController
@RequestMapping
public class HostelController{

    @Autowired
    HostelService hostelService;
    @GetMapping("/hostels")
    @PreAuthorize("hasAnyRole('ROLE_OFFICE_ADMIN', 'ROLE_STUDENT')")
    public ResponseEntity<List<HostelDto>> gethostels(){
        List<HostelDto> hostels = this.hostelService.getAllHostels();
        return ResponseEntity.ok(hostels);
    }

    @PostMapping("/hostels")
    @PreAuthorize("hasRole('ROLE_OFFICE_ADMIN')")
    public ResponseEntity<HostelDto> createHostel(@Valid @RequestBody HostelCreationDto hostelCreationDto){
        HostelDto hostelDto = this.hostelService.createHostel(hostelCreationDto);
        return new ResponseEntity<>(hostelDto, HttpStatus.CREATED);
    }
}
