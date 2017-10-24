package com.augwit.myapp.service.impl;

import com.augwit.myapp.service.CourceService;
import com.augwit.myapp.domain.Cource;
import com.augwit.myapp.repository.CourceRepository;
import com.augwit.myapp.service.dto.CourceDTO;
import com.augwit.myapp.service.mapper.CourceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing Cource.
 */
@Service
@Transactional
public class CourceServiceImpl implements CourceService{

    private final Logger log = LoggerFactory.getLogger(CourceServiceImpl.class);

    private final CourceRepository courceRepository;

    private final CourceMapper courceMapper;

    public CourceServiceImpl(CourceRepository courceRepository, CourceMapper courceMapper) {
        this.courceRepository = courceRepository;
        this.courceMapper = courceMapper;
    }

    /**
     * Save a cource.
     *
     * @param courceDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CourceDTO save(CourceDTO courceDTO) {
        log.debug("Request to save Cource : {}", courceDTO);
        Cource cource = courceMapper.toEntity(courceDTO);
        cource = courceRepository.save(cource);
        return courceMapper.toDto(cource);
    }

    /**
     *  Get all the cources.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CourceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Cources");
        return courceRepository.findAll(pageable)
            .map(courceMapper::toDto);
    }


    /**
     *  get all the cources where Teacher is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<CourceDTO> findAllWhereTeacherIsNull() {
        log.debug("Request to get all cources where Teacher is null");
        return StreamSupport
            .stream(courceRepository.findAll().spliterator(), false)
            .filter(cource -> cource.getTeacher() == null)
            .map(courceMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one cource by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CourceDTO findOne(Long id) {
        log.debug("Request to get Cource : {}", id);
        Cource cource = courceRepository.findOne(id);
        return courceMapper.toDto(cource);
    }

    /**
     *  Delete the  cource by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Cource : {}", id);
        courceRepository.delete(id);
    }
}
