package com.augwit.myapp.web.rest;

import com.augwit.myapp.MyApp;

import com.augwit.myapp.domain.Cource;
import com.augwit.myapp.repository.CourceRepository;
import com.augwit.myapp.service.CourceService;
import com.augwit.myapp.service.dto.CourceDTO;
import com.augwit.myapp.service.mapper.CourceMapper;
import com.augwit.myapp.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the CourceResource REST controller.
 *
 * @see CourceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyApp.class)
public class CourceResourceIntTest {

    private static final Long DEFAULT_COURCE_ID = 1L;
    private static final Long UPDATED_COURCE_ID = 2L;

    private static final String DEFAULT_COURCE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COURCE_NAME = "BBBBBBBBBB";

    @Autowired
    private CourceRepository courceRepository;

    @Autowired
    private CourceMapper courceMapper;

    @Autowired
    private CourceService courceService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCourceMockMvc;

    private Cource cource;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CourceResource courceResource = new CourceResource(courceService);
        this.restCourceMockMvc = MockMvcBuilders.standaloneSetup(courceResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cource createEntity(EntityManager em) {
        Cource cource = new Cource()
            .courceId(DEFAULT_COURCE_ID)
            .courceName(DEFAULT_COURCE_NAME);
        return cource;
    }

    @Before
    public void initTest() {
        cource = createEntity(em);
    }

    @Test
    @Transactional
    public void createCource() throws Exception {
        int databaseSizeBeforeCreate = courceRepository.findAll().size();

        // Create the Cource
        CourceDTO courceDTO = courceMapper.toDto(cource);
        restCourceMockMvc.perform(post("/api/cources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courceDTO)))
            .andExpect(status().isCreated());

        // Validate the Cource in the database
        List<Cource> courceList = courceRepository.findAll();
        assertThat(courceList).hasSize(databaseSizeBeforeCreate + 1);
        Cource testCource = courceList.get(courceList.size() - 1);
        assertThat(testCource.getCourceId()).isEqualTo(DEFAULT_COURCE_ID);
        assertThat(testCource.getCourceName()).isEqualTo(DEFAULT_COURCE_NAME);
    }

    @Test
    @Transactional
    public void createCourceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = courceRepository.findAll().size();

        // Create the Cource with an existing ID
        cource.setId(1L);
        CourceDTO courceDTO = courceMapper.toDto(cource);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCourceMockMvc.perform(post("/api/cources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Cource in the database
        List<Cource> courceList = courceRepository.findAll();
        assertThat(courceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCources() throws Exception {
        // Initialize the database
        courceRepository.saveAndFlush(cource);

        // Get all the courceList
        restCourceMockMvc.perform(get("/api/cources?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cource.getId().intValue())))
            .andExpect(jsonPath("$.[*].courceId").value(hasItem(DEFAULT_COURCE_ID.intValue())))
            .andExpect(jsonPath("$.[*].courceName").value(hasItem(DEFAULT_COURCE_NAME.toString())));
    }

    @Test
    @Transactional
    public void getCource() throws Exception {
        // Initialize the database
        courceRepository.saveAndFlush(cource);

        // Get the cource
        restCourceMockMvc.perform(get("/api/cources/{id}", cource.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cource.getId().intValue()))
            .andExpect(jsonPath("$.courceId").value(DEFAULT_COURCE_ID.intValue()))
            .andExpect(jsonPath("$.courceName").value(DEFAULT_COURCE_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCource() throws Exception {
        // Get the cource
        restCourceMockMvc.perform(get("/api/cources/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCource() throws Exception {
        // Initialize the database
        courceRepository.saveAndFlush(cource);
        int databaseSizeBeforeUpdate = courceRepository.findAll().size();

        // Update the cource
        Cource updatedCource = courceRepository.findOne(cource.getId());
        updatedCource
            .courceId(UPDATED_COURCE_ID)
            .courceName(UPDATED_COURCE_NAME);
        CourceDTO courceDTO = courceMapper.toDto(updatedCource);

        restCourceMockMvc.perform(put("/api/cources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courceDTO)))
            .andExpect(status().isOk());

        // Validate the Cource in the database
        List<Cource> courceList = courceRepository.findAll();
        assertThat(courceList).hasSize(databaseSizeBeforeUpdate);
        Cource testCource = courceList.get(courceList.size() - 1);
        assertThat(testCource.getCourceId()).isEqualTo(UPDATED_COURCE_ID);
        assertThat(testCource.getCourceName()).isEqualTo(UPDATED_COURCE_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingCource() throws Exception {
        int databaseSizeBeforeUpdate = courceRepository.findAll().size();

        // Create the Cource
        CourceDTO courceDTO = courceMapper.toDto(cource);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCourceMockMvc.perform(put("/api/cources")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(courceDTO)))
            .andExpect(status().isCreated());

        // Validate the Cource in the database
        List<Cource> courceList = courceRepository.findAll();
        assertThat(courceList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCource() throws Exception {
        // Initialize the database
        courceRepository.saveAndFlush(cource);
        int databaseSizeBeforeDelete = courceRepository.findAll().size();

        // Get the cource
        restCourceMockMvc.perform(delete("/api/cources/{id}", cource.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Cource> courceList = courceRepository.findAll();
        assertThat(courceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Cource.class);
        Cource cource1 = new Cource();
        cource1.setId(1L);
        Cource cource2 = new Cource();
        cource2.setId(cource1.getId());
        assertThat(cource1).isEqualTo(cource2);
        cource2.setId(2L);
        assertThat(cource1).isNotEqualTo(cource2);
        cource1.setId(null);
        assertThat(cource1).isNotEqualTo(cource2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CourceDTO.class);
        CourceDTO courceDTO1 = new CourceDTO();
        courceDTO1.setId(1L);
        CourceDTO courceDTO2 = new CourceDTO();
        assertThat(courceDTO1).isNotEqualTo(courceDTO2);
        courceDTO2.setId(courceDTO1.getId());
        assertThat(courceDTO1).isEqualTo(courceDTO2);
        courceDTO2.setId(2L);
        assertThat(courceDTO1).isNotEqualTo(courceDTO2);
        courceDTO1.setId(null);
        assertThat(courceDTO1).isNotEqualTo(courceDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(courceMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(courceMapper.fromId(null)).isNull();
    }
}
