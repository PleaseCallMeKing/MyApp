package com.augwit.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.augwit.myapp.service.CourceService;
import com.augwit.myapp.web.rest.errors.BadRequestAlertException;
import com.augwit.myapp.web.rest.util.HeaderUtil;
import com.augwit.myapp.web.rest.util.PaginationUtil;
import com.augwit.myapp.service.dto.CourceDTO;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing Cource.
 */
@RestController
@RequestMapping("/api")
public class CourceResource {

    private final Logger log = LoggerFactory.getLogger(CourceResource.class);

    private static final String ENTITY_NAME = "cource";

    private final CourceService courceService;

    public CourceResource(CourceService courceService) {
        this.courceService = courceService;
    }

    /**
     * POST  /cources : Create a new cource.
     *
     * @param courceDTO the courceDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new courceDTO, or with status 400 (Bad Request) if the cource has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cources")
    @Timed
    public ResponseEntity<CourceDTO> createCource(@RequestBody CourceDTO courceDTO) throws URISyntaxException {
        log.debug("REST request to save Cource : {}", courceDTO);
        if (courceDTO.getId() != null) {
            throw new BadRequestAlertException("A new cource cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CourceDTO result = courceService.save(courceDTO);
        return ResponseEntity.created(new URI("/api/cources/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cources : Updates an existing cource.
     *
     * @param courceDTO the courceDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated courceDTO,
     * or with status 400 (Bad Request) if the courceDTO is not valid,
     * or with status 500 (Internal Server Error) if the courceDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cources")
    @Timed
    public ResponseEntity<CourceDTO> updateCource(@RequestBody CourceDTO courceDTO) throws URISyntaxException {
        log.debug("REST request to update Cource : {}", courceDTO);
        if (courceDTO.getId() == null) {
            return createCource(courceDTO);
        }
        CourceDTO result = courceService.save(courceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, courceDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cources : get all the cources.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of cources in body
     */
    @GetMapping("/cources")
    @Timed
    public ResponseEntity<List<CourceDTO>> getAllCources(@ApiParam Pageable pageable, @RequestParam(required = false) String filter) {
        if ("teacher-is-null".equals(filter)) {
            log.debug("REST request to get all Cources where teacher is null");
            return new ResponseEntity<>(courceService.findAllWhereTeacherIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of Cources");
        Page<CourceDTO> page = courceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/cources");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /cources/:id : get the "id" cource.
     *
     * @param id the id of the courceDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the courceDTO, or with status 404 (Not Found)
     */
    @GetMapping("/cources/{id}")
    @Timed
    public ResponseEntity<CourceDTO> getCource(@PathVariable Long id) {
        log.debug("REST request to get Cource : {}", id);
        CourceDTO courceDTO = courceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(courceDTO));
    }

    /**
     * DELETE  /cources/:id : delete the "id" cource.
     *
     * @param id the id of the courceDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cources/{id}")
    @Timed
    public ResponseEntity<Void> deleteCource(@PathVariable Long id) {
        log.debug("REST request to delete Cource : {}", id);
        courceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
