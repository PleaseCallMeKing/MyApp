package com.augwit.myapp.service;

import com.augwit.myapp.service.dto.CourceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing Cource.
 */
public interface CourceService {

    /**
     * Save a cource.
     *
     * @param courceDTO the entity to save
     * @return the persisted entity
     */
    CourceDTO save(CourceDTO courceDTO);

    /**
     *  Get all the cources.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<CourceDTO> findAll(Pageable pageable);
    /**
     *  Get all the CourceDTO where Teacher is null.
     *
     *  @return the list of entities
     */
    List<CourceDTO> findAllWhereTeacherIsNull();

    /**
     *  Get the "id" cource.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    CourceDTO findOne(Long id);

    /**
     *  Delete the "id" cource.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
