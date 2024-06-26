package com.jwtauth.schoolauthorization.Repository;
import com.jwtauth.schoolauthorization.Entity.TeacherEntity;
import com.jwtauth.schoolauthorization.config.SchoolDataSource.SchoolDatabaseConnMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@SchoolDatabaseConnMapper
public interface TeacherRepository {

    List<TeacherEntity> findAllTeachers(@Param("minAge") Integer minAge, @Param("maxAge") Integer maxAge, @Param("gender") String gender, @Param("subject") String subject);
    TeacherEntity findTeacherById(Integer id);

    void addTeacher(TeacherEntity teacherEntity);
}
