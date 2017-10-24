package com.augwit.myapp.service.mapper;

import com.augwit.myapp.domain.*;
import com.augwit.myapp.service.dto.TeacherDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Teacher and its DTO TeacherDTO.
 */
@Mapper(componentModel = "spring", uses = {CourceMapper.class})
public interface TeacherMapper extends EntityMapper<TeacherDTO, Teacher> {

    @Mapping(source = "cource.id", target = "courceId")
    @Mapping(source = "cource.courceId", target = "courceCourceId")
    TeacherDTO toDto(Teacher teacher); 

    @Mapping(source = "courceId", target = "cource")
    @Mapping(target = "students", ignore = true)
    Teacher toEntity(TeacherDTO teacherDTO);

    default Teacher fromId(Long id) {
        if (id == null) {
            return null;
        }
        Teacher teacher = new Teacher();
        teacher.setId(id);
        return teacher;
    }
}
