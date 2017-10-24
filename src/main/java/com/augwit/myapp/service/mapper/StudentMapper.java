package com.augwit.myapp.service.mapper;

import com.augwit.myapp.domain.*;
import com.augwit.myapp.service.dto.StudentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Student and its DTO StudentDTO.
 */
@Mapper(componentModel = "spring", uses = {CourceMapper.class, TeacherMapper.class})
public interface StudentMapper extends EntityMapper<StudentDTO, Student> {

    @Mapping(source = "teacher.id", target = "teacherId")
    @Mapping(source = "teacher.teacherNo", target = "teacherTeacherNo")
    StudentDTO toDto(Student student); 

    @Mapping(source = "teacherId", target = "teacher")
    Student toEntity(StudentDTO studentDTO);

    default Student fromId(Long id) {
        if (id == null) {
            return null;
        }
        Student student = new Student();
        student.setId(id);
        return student;
    }
}
