package com.jwtauth.schoolauthorization.service;
import com.jwtauth.schoolauthorization.dto.*;
import com.jwtauth.schoolauthorization.entity.StudentEntity;
import com.jwtauth.schoolauthorization.entity.SubjectEntity;
import com.jwtauth.schoolauthorization.exception.ResourceNotFoundException;
import com.jwtauth.schoolauthorization.exception.UnauthorizedException;
import com.jwtauth.schoolauthorization.mapstruct.SubjectMapper;
import com.jwtauth.schoolauthorization.mapstruct.StudentMapper;
import com.jwtauth.schoolauthorization.repository.StudentRepository;
import com.jwtauth.schoolauthorization.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentMapper studentMapper;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    SubjectMapper subjectMapper;



    public List<StudentDtoForList> getAllStudent() {
        List<StudentEntity> studentEntityList = this.studentRepository.findAllStudents();
        return this.studentMapper.toDtoList(studentEntityList);
    }



  public StudentDtoForSubject getStudentById(Integer id){

    StudentEntity studentEntity = this.studentRepository.findStudentById(id);
    if (studentEntity == null) {
            throw new ResourceNotFoundException("student with id " + id + " not found");
    }
        return this.studentMapper.toDtoForSubject(studentEntity);
    }

    public StudentDto postStudent(StudentCreationDto studentCreationDto) {
        StudentEntity studentEntity = this.studentMapper.toEntity(studentCreationDto);
        this.studentRepository.addStudent(studentEntity);
        return this.studentMapper.toDto(studentEntity);
    }

    public StudentDto assignSubjectsToStudent(Integer id, List<SubjectDto> subjectDtoList) {
        StudentEntity studentEntity = this.studentRepository.findStudentById(id);
        if (studentEntity == null) {
            throw new ResourceNotFoundException("student not found with id " + id);
        }
        if (subjectDtoList != null) {
            List<SubjectEntity> subjectEntities = this.subjectRepository.findAllSubjects();
            for (SubjectDto subjectDto : subjectDtoList) {
                Integer subId = subjectDto.getId();
                int flag = 0;
                for (SubjectEntity subjectEntity : subjectEntities) {
                    if (subjectEntity.getId().equals(subId)) {
                        flag = 1;
                        break;
                    }

                }

            }


        }
        return this.studentMapper.toDto(this.studentRepository.findStudentById(id));
    }

}