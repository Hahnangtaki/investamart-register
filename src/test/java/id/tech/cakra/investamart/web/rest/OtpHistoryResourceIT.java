package id.tech.cakra.investamart.web.rest;

import id.tech.cakra.investamart.InvestaregistersvcApp;
import id.tech.cakra.investamart.domain.OtpHistory;
import id.tech.cakra.investamart.repository.OtpHistoryRepository;
import id.tech.cakra.investamart.service.OtpHistoryService;
import id.tech.cakra.investamart.service.dto.OtpHistoryDTO;
import id.tech.cakra.investamart.service.mapper.OtpHistoryMapper;
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
 * Integration tests for the {@link OtpHistoryResource} REST controller.
 */
@SpringBootTest(classes = InvestaregistersvcApp.class)
public class OtpHistoryResourceIT {

    private static final Integer DEFAULT_CREATED_BY = 1;
    private static final Integer UPDATED_CREATED_BY = 2;

    private static final ZonedDateTime DEFAULT_OTP_CREATED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_OTP_CREATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_OTP_EXPIRED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_OTP_EXPIRED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_MEMBER_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_MEMBER_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final Integer DEFAULT_PROC_TYPE = 1;
    private static final Integer UPDATED_PROC_TYPE = 2;

    private static final String DEFAULT_TOKEN_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_TOKEN_TEXT = "BBBBBBBBBB";

    private static final String DEFAULT_TOKEN = "AAAAAAAAAA";
    private static final String UPDATED_TOKEN = "BBBBBBBBBB";

    private static final Integer DEFAULT_RETRY_COUNT = 1;
    private static final Integer UPDATED_RETRY_COUNT = 2;

    private static final Integer DEFAULT_RETRY_MAX = 1;
    private static final Integer UPDATED_RETRY_MAX = 2;

    private static final byte[] DEFAULT_REQUEST_DATA = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_REQUEST_DATA = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_REQUEST_DATA_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_REQUEST_DATA_CONTENT_TYPE = "image/png";

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
    private OtpHistoryRepository otpHistoryRepository;

    @Autowired
    private OtpHistoryMapper otpHistoryMapper;

    @Autowired
    private OtpHistoryService otpHistoryService;

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

    private MockMvc restOtpHistoryMockMvc;

    private OtpHistory otpHistory;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OtpHistoryResource otpHistoryResource = new OtpHistoryResource(otpHistoryService);
        this.restOtpHistoryMockMvc = MockMvcBuilders.standaloneSetup(otpHistoryResource)
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
    public static OtpHistory createEntity(EntityManager em) {
        OtpHistory otpHistory = new OtpHistory()
            .createdBy(DEFAULT_CREATED_BY)
            .otpCreatedDate(DEFAULT_OTP_CREATED_DATE)
            .otpExpiredDate(DEFAULT_OTP_EXPIRED_DATE)
            .memberEmail(DEFAULT_MEMBER_EMAIL)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .procType(DEFAULT_PROC_TYPE)
            .tokenText(DEFAULT_TOKEN_TEXT)
            .token(DEFAULT_TOKEN)
            .retryCount(DEFAULT_RETRY_COUNT)
            .retryMax(DEFAULT_RETRY_MAX)
            .requestData(DEFAULT_REQUEST_DATA)
            .requestDataContentType(DEFAULT_REQUEST_DATA_CONTENT_TYPE)
            .createSystemDate(DEFAULT_CREATE_SYSTEM_DATE)
            .createDate(DEFAULT_CREATE_DATE)
            .createUserId(DEFAULT_CREATE_USER_ID)
            .lastModificationSystemDate(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(DEFAULT_LAST_MODIFICATION_DATE)
            .lastModificationUserId(DEFAULT_LAST_MODIFICATION_USER_ID);
        return otpHistory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OtpHistory createUpdatedEntity(EntityManager em) {
        OtpHistory otpHistory = new OtpHistory()
            .createdBy(UPDATED_CREATED_BY)
            .otpCreatedDate(UPDATED_OTP_CREATED_DATE)
            .otpExpiredDate(UPDATED_OTP_EXPIRED_DATE)
            .memberEmail(UPDATED_MEMBER_EMAIL)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .procType(UPDATED_PROC_TYPE)
            .tokenText(UPDATED_TOKEN_TEXT)
            .token(UPDATED_TOKEN)
            .retryCount(UPDATED_RETRY_COUNT)
            .retryMax(UPDATED_RETRY_MAX)
            .requestData(UPDATED_REQUEST_DATA)
            .requestDataContentType(UPDATED_REQUEST_DATA_CONTENT_TYPE)
            .createSystemDate(UPDATED_CREATE_SYSTEM_DATE)
            .createDate(UPDATED_CREATE_DATE)
            .createUserId(UPDATED_CREATE_USER_ID)
            .lastModificationSystemDate(UPDATED_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(UPDATED_LAST_MODIFICATION_DATE)
            .lastModificationUserId(UPDATED_LAST_MODIFICATION_USER_ID);
        return otpHistory;
    }

    @BeforeEach
    public void initTest() {
        otpHistory = createEntity(em);
    }

    @Test
    @Transactional
    public void createOtpHistory() throws Exception {
        int databaseSizeBeforeCreate = otpHistoryRepository.findAll().size();

        // Create the OtpHistory
        OtpHistoryDTO otpHistoryDTO = otpHistoryMapper.toDto(otpHistory);
        restOtpHistoryMockMvc.perform(post("/api/otp-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(otpHistoryDTO)))
            .andExpect(status().isCreated());

        // Validate the OtpHistory in the database
        List<OtpHistory> otpHistoryList = otpHistoryRepository.findAll();
        assertThat(otpHistoryList).hasSize(databaseSizeBeforeCreate + 1);
        OtpHistory testOtpHistory = otpHistoryList.get(otpHistoryList.size() - 1);
        assertThat(testOtpHistory.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testOtpHistory.getOtpCreatedDate()).isEqualTo(DEFAULT_OTP_CREATED_DATE);
        assertThat(testOtpHistory.getOtpExpiredDate()).isEqualTo(DEFAULT_OTP_EXPIRED_DATE);
        assertThat(testOtpHistory.getMemberEmail()).isEqualTo(DEFAULT_MEMBER_EMAIL);
        assertThat(testOtpHistory.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testOtpHistory.getProcType()).isEqualTo(DEFAULT_PROC_TYPE);
        assertThat(testOtpHistory.getTokenText()).isEqualTo(DEFAULT_TOKEN_TEXT);
        assertThat(testOtpHistory.getToken()).isEqualTo(DEFAULT_TOKEN);
        assertThat(testOtpHistory.getRetryCount()).isEqualTo(DEFAULT_RETRY_COUNT);
        assertThat(testOtpHistory.getRetryMax()).isEqualTo(DEFAULT_RETRY_MAX);
        assertThat(testOtpHistory.getRequestData()).isEqualTo(DEFAULT_REQUEST_DATA);
        assertThat(testOtpHistory.getRequestDataContentType()).isEqualTo(DEFAULT_REQUEST_DATA_CONTENT_TYPE);
        assertThat(testOtpHistory.getCreateSystemDate()).isEqualTo(DEFAULT_CREATE_SYSTEM_DATE);
        assertThat(testOtpHistory.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testOtpHistory.getCreateUserId()).isEqualTo(DEFAULT_CREATE_USER_ID);
        assertThat(testOtpHistory.getLastModificationSystemDate()).isEqualTo(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE);
        assertThat(testOtpHistory.getLastModificationDate()).isEqualTo(DEFAULT_LAST_MODIFICATION_DATE);
        assertThat(testOtpHistory.getLastModificationUserId()).isEqualTo(DEFAULT_LAST_MODIFICATION_USER_ID);
    }

    @Test
    @Transactional
    public void createOtpHistoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = otpHistoryRepository.findAll().size();

        // Create the OtpHistory with an existing ID
        otpHistory.setId(1L);
        OtpHistoryDTO otpHistoryDTO = otpHistoryMapper.toDto(otpHistory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOtpHistoryMockMvc.perform(post("/api/otp-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(otpHistoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OtpHistory in the database
        List<OtpHistory> otpHistoryList = otpHistoryRepository.findAll();
        assertThat(otpHistoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllOtpHistories() throws Exception {
        // Initialize the database
        otpHistoryRepository.saveAndFlush(otpHistory);

        // Get all the otpHistoryList
        restOtpHistoryMockMvc.perform(get("/api/otp-histories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(otpHistory.getId().intValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].otpCreatedDate").value(hasItem(sameInstant(DEFAULT_OTP_CREATED_DATE))))
            .andExpect(jsonPath("$.[*].otpExpiredDate").value(hasItem(sameInstant(DEFAULT_OTP_EXPIRED_DATE))))
            .andExpect(jsonPath("$.[*].memberEmail").value(hasItem(DEFAULT_MEMBER_EMAIL)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].procType").value(hasItem(DEFAULT_PROC_TYPE)))
            .andExpect(jsonPath("$.[*].tokenText").value(hasItem(DEFAULT_TOKEN_TEXT)))
            .andExpect(jsonPath("$.[*].token").value(hasItem(DEFAULT_TOKEN)))
            .andExpect(jsonPath("$.[*].retryCount").value(hasItem(DEFAULT_RETRY_COUNT)))
            .andExpect(jsonPath("$.[*].retryMax").value(hasItem(DEFAULT_RETRY_MAX)))
            .andExpect(jsonPath("$.[*].requestDataContentType").value(hasItem(DEFAULT_REQUEST_DATA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].requestData").value(hasItem(Base64Utils.encodeToString(DEFAULT_REQUEST_DATA))))
            .andExpect(jsonPath("$.[*].createSystemDate").value(hasItem(DEFAULT_CREATE_SYSTEM_DATE.toString())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(sameInstant(DEFAULT_CREATE_DATE))))
            .andExpect(jsonPath("$.[*].createUserId").value(hasItem(DEFAULT_CREATE_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].lastModificationSystemDate").value(hasItem(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModificationDate").value(hasItem(sameInstant(DEFAULT_LAST_MODIFICATION_DATE))))
            .andExpect(jsonPath("$.[*].lastModificationUserId").value(hasItem(DEFAULT_LAST_MODIFICATION_USER_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getOtpHistory() throws Exception {
        // Initialize the database
        otpHistoryRepository.saveAndFlush(otpHistory);

        // Get the otpHistory
        restOtpHistoryMockMvc.perform(get("/api/otp-histories/{id}", otpHistory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(otpHistory.getId().intValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.otpCreatedDate").value(sameInstant(DEFAULT_OTP_CREATED_DATE)))
            .andExpect(jsonPath("$.otpExpiredDate").value(sameInstant(DEFAULT_OTP_EXPIRED_DATE)))
            .andExpect(jsonPath("$.memberEmail").value(DEFAULT_MEMBER_EMAIL))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER))
            .andExpect(jsonPath("$.procType").value(DEFAULT_PROC_TYPE))
            .andExpect(jsonPath("$.tokenText").value(DEFAULT_TOKEN_TEXT))
            .andExpect(jsonPath("$.token").value(DEFAULT_TOKEN))
            .andExpect(jsonPath("$.retryCount").value(DEFAULT_RETRY_COUNT))
            .andExpect(jsonPath("$.retryMax").value(DEFAULT_RETRY_MAX))
            .andExpect(jsonPath("$.requestDataContentType").value(DEFAULT_REQUEST_DATA_CONTENT_TYPE))
            .andExpect(jsonPath("$.requestData").value(Base64Utils.encodeToString(DEFAULT_REQUEST_DATA)))
            .andExpect(jsonPath("$.createSystemDate").value(DEFAULT_CREATE_SYSTEM_DATE.toString()))
            .andExpect(jsonPath("$.createDate").value(sameInstant(DEFAULT_CREATE_DATE)))
            .andExpect(jsonPath("$.createUserId").value(DEFAULT_CREATE_USER_ID.intValue()))
            .andExpect(jsonPath("$.lastModificationSystemDate").value(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE.toString()))
            .andExpect(jsonPath("$.lastModificationDate").value(sameInstant(DEFAULT_LAST_MODIFICATION_DATE)))
            .andExpect(jsonPath("$.lastModificationUserId").value(DEFAULT_LAST_MODIFICATION_USER_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingOtpHistory() throws Exception {
        // Get the otpHistory
        restOtpHistoryMockMvc.perform(get("/api/otp-histories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOtpHistory() throws Exception {
        // Initialize the database
        otpHistoryRepository.saveAndFlush(otpHistory);

        int databaseSizeBeforeUpdate = otpHistoryRepository.findAll().size();

        // Update the otpHistory
        OtpHistory updatedOtpHistory = otpHistoryRepository.findById(otpHistory.getId()).get();
        // Disconnect from session so that the updates on updatedOtpHistory are not directly saved in db
        em.detach(updatedOtpHistory);
        updatedOtpHistory
            .createdBy(UPDATED_CREATED_BY)
            .otpCreatedDate(UPDATED_OTP_CREATED_DATE)
            .otpExpiredDate(UPDATED_OTP_EXPIRED_DATE)
            .memberEmail(UPDATED_MEMBER_EMAIL)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .procType(UPDATED_PROC_TYPE)
            .tokenText(UPDATED_TOKEN_TEXT)
            .token(UPDATED_TOKEN)
            .retryCount(UPDATED_RETRY_COUNT)
            .retryMax(UPDATED_RETRY_MAX)
            .requestData(UPDATED_REQUEST_DATA)
            .requestDataContentType(UPDATED_REQUEST_DATA_CONTENT_TYPE)
            .createSystemDate(UPDATED_CREATE_SYSTEM_DATE)
            .createDate(UPDATED_CREATE_DATE)
            .createUserId(UPDATED_CREATE_USER_ID)
            .lastModificationSystemDate(UPDATED_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(UPDATED_LAST_MODIFICATION_DATE)
            .lastModificationUserId(UPDATED_LAST_MODIFICATION_USER_ID);
        OtpHistoryDTO otpHistoryDTO = otpHistoryMapper.toDto(updatedOtpHistory);

        restOtpHistoryMockMvc.perform(put("/api/otp-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(otpHistoryDTO)))
            .andExpect(status().isOk());

        // Validate the OtpHistory in the database
        List<OtpHistory> otpHistoryList = otpHistoryRepository.findAll();
        assertThat(otpHistoryList).hasSize(databaseSizeBeforeUpdate);
        OtpHistory testOtpHistory = otpHistoryList.get(otpHistoryList.size() - 1);
        assertThat(testOtpHistory.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testOtpHistory.getOtpCreatedDate()).isEqualTo(UPDATED_OTP_CREATED_DATE);
        assertThat(testOtpHistory.getOtpExpiredDate()).isEqualTo(UPDATED_OTP_EXPIRED_DATE);
        assertThat(testOtpHistory.getMemberEmail()).isEqualTo(UPDATED_MEMBER_EMAIL);
        assertThat(testOtpHistory.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testOtpHistory.getProcType()).isEqualTo(UPDATED_PROC_TYPE);
        assertThat(testOtpHistory.getTokenText()).isEqualTo(UPDATED_TOKEN_TEXT);
        assertThat(testOtpHistory.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testOtpHistory.getRetryCount()).isEqualTo(UPDATED_RETRY_COUNT);
        assertThat(testOtpHistory.getRetryMax()).isEqualTo(UPDATED_RETRY_MAX);
        assertThat(testOtpHistory.getRequestData()).isEqualTo(UPDATED_REQUEST_DATA);
        assertThat(testOtpHistory.getRequestDataContentType()).isEqualTo(UPDATED_REQUEST_DATA_CONTENT_TYPE);
        assertThat(testOtpHistory.getCreateSystemDate()).isEqualTo(UPDATED_CREATE_SYSTEM_DATE);
        assertThat(testOtpHistory.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testOtpHistory.getCreateUserId()).isEqualTo(UPDATED_CREATE_USER_ID);
        assertThat(testOtpHistory.getLastModificationSystemDate()).isEqualTo(UPDATED_LAST_MODIFICATION_SYSTEM_DATE);
        assertThat(testOtpHistory.getLastModificationDate()).isEqualTo(UPDATED_LAST_MODIFICATION_DATE);
        assertThat(testOtpHistory.getLastModificationUserId()).isEqualTo(UPDATED_LAST_MODIFICATION_USER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingOtpHistory() throws Exception {
        int databaseSizeBeforeUpdate = otpHistoryRepository.findAll().size();

        // Create the OtpHistory
        OtpHistoryDTO otpHistoryDTO = otpHistoryMapper.toDto(otpHistory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOtpHistoryMockMvc.perform(put("/api/otp-histories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(otpHistoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OtpHistory in the database
        List<OtpHistory> otpHistoryList = otpHistoryRepository.findAll();
        assertThat(otpHistoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOtpHistory() throws Exception {
        // Initialize the database
        otpHistoryRepository.saveAndFlush(otpHistory);

        int databaseSizeBeforeDelete = otpHistoryRepository.findAll().size();

        // Delete the otpHistory
        restOtpHistoryMockMvc.perform(delete("/api/otp-histories/{id}", otpHistory.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OtpHistory> otpHistoryList = otpHistoryRepository.findAll();
        assertThat(otpHistoryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
