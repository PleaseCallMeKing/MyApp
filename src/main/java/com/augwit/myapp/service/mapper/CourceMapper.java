package com.augwit.myapp.service.mapper;

import com.augwit.myapp.domain.*;
import com.augwit.myapp.service.dto.CourceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Cource and its DTO CourceDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CourceMapper extends EntityMapper<CourceDTO, Cource> {

    

    @Mapping(target = "teacher", ignore = true)
    @Mapping(target = "students", ignore = true)
    Cource toEntity(CourceDTO courceDTO);

    default Cource fromId(Long id) {
        if (id == null) {
            return null;
        }
        Cource cource = new Cource();
        cource.setId(id);
        return cource;
    }
}
