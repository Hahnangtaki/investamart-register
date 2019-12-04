package id.tech.cakra.investamart.web.rest;

import id.tech.cakra.investamart.InvestaregistersvcApp;
import id.tech.cakra.investamart.domain.ImageRegister;
import id.tech.cakra.investamart.repository.ImageRegisterRepository;
import id.tech.cakra.investamart.service.ImageRegisterService;
import id.tech.cakra.investamart.service.dto.ImageRegisterDTO;
import id.tech.cakra.investamart.service.mapper.ImageRegisterMapper;
import id.tech.cakra.investamart.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static id.tech.cakra.investamart.web.rest.TestUtil.sameInstant;
import static id.tech.cakra.investamart.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link ImageRegisterResource} REST controller.
 */
@SpringBootTest(classes = InvestaregistersvcApp.class)
public class ImageRegisterResourceIT {

    private static final byte[] DEFAULT_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_IMAGE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_CODE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATE_SYSTEM_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATE_SYSTEM_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final ZonedDateTime DEFAULT_CREATE_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Long DEFAULT_CREATE_USER_ID = 1L;
    private static final Long UPDATED_CREATE_USER_ID = 2L;

    private static final LocalDate DEFAULT_LAST_MODIFICATION_SYSTEM_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LAST_MODIFICATION_SYSTEM_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final ZonedDateTime DEFAULT_LAST_MODIFICATION_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_LAST_MODIFICATION_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Long DEFAULT_LAST_MODIFICATION_USER_ID = 1L;
    private static final Long UPDATED_LAST_MODIFICATION_USER_ID = 2L;

    @Autowired
    private ImageRegisterRepository imageRegisterRepository;

    @Autowired
    private ImageRegisterMapper imageRegisterMapper;

    @Autowired
    private ImageRegisterService imageRegisterService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restImageRegisterMockMvc;

    private ImageRegister imageRegister;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ImageRegisterResource imageRegisterResource = new ImageRegisterResource(imageRegisterService);
        this.restImageRegisterMockMvc = MockMvcBuilders.standaloneSetup(imageRegisterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ImageRegister createEntity(EntityManager em) {
        ImageRegister imageRegister = new ImageRegister()
            .image(DEFAULT_IMAGE)
            .imageContentType(DEFAULT_IMAGE_CONTENT_TYPE)
            .imageCode(DEFAULT_IMAGE_CODE)
            .createSystemDate(DEFAULT_CREATE_SYSTEM_DATE)
            .createDate(DEFAULT_CREATE_DATE)
            .createUserId(DEFAULT_CREATE_USER_ID)
            .lastModificationSystemDate(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(DEFAULT_LAST_MODIFICATION_DATE)
            .lastModificationUserId(DEFAULT_LAST_MODIFICATION_USER_ID);
        return imageRegister;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ImageRegister createUpdatedEntity(EntityManager em) {
        ImageRegister imageRegister = new ImageRegister()
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE)
            .imageCode(UPDATED_IMAGE_CODE)
            .createSystemDate(UPDATED_CREATE_SYSTEM_DATE)
            .createDate(UPDATED_CREATE_DATE)
            .createUserId(UPDATED_CREATE_USER_ID)
            .lastModificationSystemDate(UPDATED_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(UPDATED_LAST_MODIFICATION_DATE)
            .lastModificationUserId(UPDATED_LAST_MODIFICATION_USER_ID);
        return imageRegister;
    }

    @BeforeEach
    public void initTest() {
        imageRegister = createEntity(em);
    }

    @Test
    @Transactional
    public void createImageRegister() throws Exception {
        int databaseSizeBeforeCreate = imageRegisterRepository.findAll().size();

        // Create the ImageRegister
        ImageRegisterDTO imageRegisterDTO = imageRegisterMapper.toDto(imageRegister);
        restImageRegisterMockMvc.perform(post("/api/image-registers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(imageRegisterDTO)))
            .andExpect(status().isCreated());

        // Validate the ImageRegister in the database
        List<ImageRegister> imageRegisterList = imageRegisterRepository.findAll();
        assertThat(imageRegisterList).hasSize(databaseSizeBeforeCreate + 1);
        ImageRegister testImageRegister = imageRegisterList.get(imageRegisterList.size() - 1);
        assertThat(testImageRegister.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testImageRegister.getImageContentType()).isEqualTo(DEFAULT_IMAGE_CONTENT_TYPE);
        assertThat(testImageRegister.getImageCode()).isEqualTo(DEFAULT_IMAGE_CODE);
        assertThat(testImageRegister.getCreateSystemDate()).isEqualTo(DEFAULT_CREATE_SYSTEM_DATE);
        assertThat(testImageRegister.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testImageRegister.getCreateUserId()).isEqualTo(DEFAULT_CREATE_USER_ID);
        assertThat(testImageRegister.getLastModificationSystemDate()).isEqualTo(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE);
        assertThat(testImageRegister.getLastModificationDate()).isEqualTo(DEFAULT_LAST_MODIFICATION_DATE);
        assertThat(testImageRegister.getLastModificationUserId()).isEqualTo(DEFAULT_LAST_MODIFICATION_USER_ID);
    }

    @Test
    @Transactional
    public void createImageRegisterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = imageRegisterRepository.findAll().size();

        // Create the ImageRegister with an existing ID
        imageRegister.setId(1L);
        ImageRegisterDTO imageRegisterDTO = imageRegisterMapper.toDto(imageRegister);

        // An entity with an existing ID cannot be created, so this API call must fail
        restImageRegisterMockMvc.perform(post("/api/image-registers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(imageRegisterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ImageRegister in the database
        List<ImageRegister> imageRegisterList = imageRegisterRepository.findAll();
        assertThat(imageRegisterList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllImageRegisters() throws Exception {
        // Initialize the database
        imageRegisterRepository.saveAndFlush(imageRegister);

        // Get all the imageRegisterList
        restImageRegisterMockMvc.perform(get("/api/image-registers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(imageRegister.getId().intValue())))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))))
            .andExpect(jsonPath("$.[*].imageCode").value(hasItem(DEFAULT_IMAGE_CODE)))
            .andExpect(jsonPath("$.[*].createSystemDate").value(hasItem(DEFAULT_CREATE_SYSTEM_DATE.toString())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(sameInstant(DEFAULT_CREATE_DATE))))
            .andExpect(jsonPath("$.[*].createUserId").value(hasItem(DEFAULT_CREATE_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].lastModificationSystemDate").value(hasItem(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModificationDate").value(hasItem(sameInstant(DEFAULT_LAST_MODIFICATION_DATE))))
            .andExpect(jsonPath("$.[*].lastModificationUserId").value(hasItem(DEFAULT_LAST_MODIFICATION_USER_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getImageRegister() throws Exception {
        // Initialize the database
        imageRegisterRepository.saveAndFlush(imageRegister);

        // Get the imageRegister
        restImageRegisterMockMvc.perform(get("/api/image-registers/{id}", imageRegister.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(imageRegister.getId().intValue()))
            .andExpect(jsonPath("$.imageContentType").value(DEFAULT_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.image").value(Base64Utils.encodeToString(DEFAULT_IMAGE)))
            .andExpect(jsonPath("$.imageCode").value(DEFAULT_IMAGE_CODE))
            .andExpect(jsonPath("$.createSystemDate").value(DEFAULT_CREATE_SYSTEM_DATE.toString()))
            .andExpect(jsonPath("$.createDate").value(sameInstant(DEFAULT_CREATE_DATE)))
            .andExpect(jsonPath("$.createUserId").value(DEFAULT_CREATE_USER_ID.intValue()))
            .andExpect(jsonPath("$.lastModificationSystemDate").value(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE.toString()))
            .andExpect(jsonPath("$.lastModificationDate").value(sameInstant(DEFAULT_LAST_MODIFICATION_DATE)))
            .andExpect(jsonPath("$.lastModificationUserId").value(DEFAULT_LAST_MODIFICATION_USER_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingImageRegister() throws Exception {
        // Get the imageRegister
        restImageRegisterMockMvc.perform(get("/api/image-registers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateImageRegister() throws Exception {
        // Initialize the database
        imageRegisterRepository.saveAndFlush(imageRegister);

        int databaseSizeBeforeUpdate = imageRegisterRepository.findAll().size();

        // Update the imageRegister
        ImageRegister updatedImageRegister = imageRegisterRepository.findById(imageRegister.getId()).get();
        // Disconnect from session so that the updates on updatedImageRegister are not directly saved in db
        em.detach(updatedImageRegister);
        updatedImageRegister
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE)
            .imageCode(UPDATED_IMAGE_CODE)
            .createSystemDate(UPDATED_CREATE_SYSTEM_DATE)
            .createDate(UPDATED_CREATE_DATE)
            .createUserId(UPDATED_CREATE_USER_ID)
            .lastModificationSystemDate(UPDATED_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(UPDATED_LAST_MODIFICATION_DATE)
            .lastModificationUserId(UPDATED_LAST_MODIFICATION_USER_ID);
        ImageRegisterDTO imageRegisterDTO = imageRegisterMapper.toDto(updatedImageRegister);

        restImageRegisterMockMvc.perform(put("/api/image-registers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(imageRegisterDTO)))
            .andExpect(status().isOk());

        // Validate the ImageRegister in the database
        List<ImageRegister> imageRegisterList = imageRegisterRepository.findAll();
        assertThat(imageRegisterList).hasSize(databaseSizeBeforeUpdate);
        ImageRegister testImageRegister = imageRegisterList.get(imageRegisterList.size() - 1);
        assertThat(testImageRegister.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testImageRegister.getImageContentType()).isEqualTo(UPDATED_IMAGE_CONTENT_TYPE);
        assertThat(testImageRegister.getImageCode()).isEqualTo(UPDATED_IMAGE_CODE);
        assertThat(testImageRegister.getCreateSystemDate()).isEqualTo(UPDATED_CREATE_SYSTEM_DATE);
        assertThat(testImageRegister.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testImageRegister.getCreateUserId()).isEqualTo(UPDATED_CREATE_USER_ID);
        assertThat(testImageRegister.getLastModificationSystemDate()).isEqualTo(UPDATED_LAST_MODIFICATION_SYSTEM_DATE);
        assertThat(testImageRegister.getLastModificationDate()).isEqualTo(UPDATED_LAST_MODIFICATION_DATE);
        assertThat(testImageRegister.getLastModificationUserId()).isEqualTo(UPDATED_LAST_MODIFICATION_USER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingImageRegister() throws Exception {
        int databaseSizeBeforeUpdate = imageRegisterRepository.findAll().size();

        // Create the ImageRegister
        ImageRegisterDTO imageRegisterDTO = imageRegisterMapper.toDto(imageRegister);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restImageRegisterMockMvc.perform(put("/api/image-registers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(imageRegisterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ImageRegister in the database
        List<ImageRegister> imageRegisterList = imageRegisterRepository.findAll();
        assertThat(imageRegisterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteImageRegister() throws Exception {
        // Initialize the database
        imageRegisterRepository.saveAndFlush(imageRegister);

        int databaseSizeBeforeDelete = imageRegisterRepository.findAll().size();

        // Delete the imageRegister
        restImageRegisterMockMvc.perform(delete("/api/image-registers/{id}", imageRegister.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ImageRegister> imageRegisterList = imageRegisterRepository.findAll();
        assertThat(imageRegisterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
