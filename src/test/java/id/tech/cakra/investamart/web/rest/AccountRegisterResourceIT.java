package id.tech.cakra.investamart.web.rest;

import id.tech.cakra.investamart.InvestaregistersvcApp;
import id.tech.cakra.investamart.domain.AccountRegister;
import id.tech.cakra.investamart.repository.AccountRegisterRepository;
import id.tech.cakra.investamart.service.AccountRegisterService;
import id.tech.cakra.investamart.service.dto.AccountRegisterDTO;
import id.tech.cakra.investamart.service.mapper.AccountRegisterMapper;
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
 * Integration tests for the {@link AccountRegisterResource} REST controller.
 */
@SpringBootTest(classes = InvestaregistersvcApp.class)
public class AccountRegisterResourceIT {

    private static final String DEFAULT_REGISTER_CODE = "AAAAAAAAAA";
    private static final String UPDATED_REGISTER_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_TYPE = "A";
    private static final String UPDATED_ACCOUNT_TYPE = "B";

    private static final Boolean DEFAULT_ACCOUNT_ANGLE = false;
    private static final Boolean UPDATED_ACCOUNT_ANGLE = true;

    private static final String DEFAULT_KSEI_CLIENT_CODE = "AAAA";
    private static final String UPDATED_KSEI_CLIENT_CODE = "BBBB";

    private static final String DEFAULT_KSEI_SUBREK = "AAAAAAAAAA";
    private static final String UPDATED_KSEI_SUBREK = "BBBBBBBBBB";

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
    private AccountRegisterRepository accountRegisterRepository;

    @Autowired
    private AccountRegisterMapper accountRegisterMapper;

    @Autowired
    private AccountRegisterService accountRegisterService;

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

    private MockMvc restAccountRegisterMockMvc;

    private AccountRegister accountRegister;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AccountRegisterResource accountRegisterResource = new AccountRegisterResource(accountRegisterService);
        this.restAccountRegisterMockMvc = MockMvcBuilders.standaloneSetup(accountRegisterResource)
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
    public static AccountRegister createEntity(EntityManager em) {
        AccountRegister accountRegister = new AccountRegister()
            .registerCode(DEFAULT_REGISTER_CODE)
            .accountName(DEFAULT_ACCOUNT_NAME)
            .accountType(DEFAULT_ACCOUNT_TYPE)
            .accountAngle(DEFAULT_ACCOUNT_ANGLE)
            .kseiClientCode(DEFAULT_KSEI_CLIENT_CODE)
            .kseiSubrek(DEFAULT_KSEI_SUBREK)
            .createSystemDate(DEFAULT_CREATE_SYSTEM_DATE)
            .createDate(DEFAULT_CREATE_DATE)
            .createUserId(DEFAULT_CREATE_USER_ID)
            .lastModificationSystemDate(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(DEFAULT_LAST_MODIFICATION_DATE)
            .lastModificationUserId(DEFAULT_LAST_MODIFICATION_USER_ID);
        return accountRegister;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AccountRegister createUpdatedEntity(EntityManager em) {
        AccountRegister accountRegister = new AccountRegister()
            .registerCode(UPDATED_REGISTER_CODE)
            .accountName(UPDATED_ACCOUNT_NAME)
            .accountType(UPDATED_ACCOUNT_TYPE)
            .accountAngle(UPDATED_ACCOUNT_ANGLE)
            .kseiClientCode(UPDATED_KSEI_CLIENT_CODE)
            .kseiSubrek(UPDATED_KSEI_SUBREK)
            .createSystemDate(UPDATED_CREATE_SYSTEM_DATE)
            .createDate(UPDATED_CREATE_DATE)
            .createUserId(UPDATED_CREATE_USER_ID)
            .lastModificationSystemDate(UPDATED_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(UPDATED_LAST_MODIFICATION_DATE)
            .lastModificationUserId(UPDATED_LAST_MODIFICATION_USER_ID);
        return accountRegister;
    }

    @BeforeEach
    public void initTest() {
        accountRegister = createEntity(em);
    }

    @Test
    @Transactional
    public void createAccountRegister() throws Exception {
        int databaseSizeBeforeCreate = accountRegisterRepository.findAll().size();

        // Create the AccountRegister
        AccountRegisterDTO accountRegisterDTO = accountRegisterMapper.toDto(accountRegister);
        restAccountRegisterMockMvc.perform(post("/api/account-registers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountRegisterDTO)))
            .andExpect(status().isCreated());

        // Validate the AccountRegister in the database
        List<AccountRegister> accountRegisterList = accountRegisterRepository.findAll();
        assertThat(accountRegisterList).hasSize(databaseSizeBeforeCreate + 1);
        AccountRegister testAccountRegister = accountRegisterList.get(accountRegisterList.size() - 1);
        assertThat(testAccountRegister.getRegisterCode()).isEqualTo(DEFAULT_REGISTER_CODE);
        assertThat(testAccountRegister.getAccountName()).isEqualTo(DEFAULT_ACCOUNT_NAME);
        assertThat(testAccountRegister.getAccountType()).isEqualTo(DEFAULT_ACCOUNT_TYPE);
        assertThat(testAccountRegister.isAccountAngle()).isEqualTo(DEFAULT_ACCOUNT_ANGLE);
        assertThat(testAccountRegister.getKseiClientCode()).isEqualTo(DEFAULT_KSEI_CLIENT_CODE);
        assertThat(testAccountRegister.getKseiSubrek()).isEqualTo(DEFAULT_KSEI_SUBREK);
        assertThat(testAccountRegister.getCreateSystemDate()).isEqualTo(DEFAULT_CREATE_SYSTEM_DATE);
        assertThat(testAccountRegister.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testAccountRegister.getCreateUserId()).isEqualTo(DEFAULT_CREATE_USER_ID);
        assertThat(testAccountRegister.getLastModificationSystemDate()).isEqualTo(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE);
        assertThat(testAccountRegister.getLastModificationDate()).isEqualTo(DEFAULT_LAST_MODIFICATION_DATE);
        assertThat(testAccountRegister.getLastModificationUserId()).isEqualTo(DEFAULT_LAST_MODIFICATION_USER_ID);
    }

    @Test
    @Transactional
    public void createAccountRegisterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = accountRegisterRepository.findAll().size();

        // Create the AccountRegister with an existing ID
        accountRegister.setId(1L);
        AccountRegisterDTO accountRegisterDTO = accountRegisterMapper.toDto(accountRegister);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAccountRegisterMockMvc.perform(post("/api/account-registers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountRegisterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AccountRegister in the database
        List<AccountRegister> accountRegisterList = accountRegisterRepository.findAll();
        assertThat(accountRegisterList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAccountRegisters() throws Exception {
        // Initialize the database
        accountRegisterRepository.saveAndFlush(accountRegister);

        // Get all the accountRegisterList
        restAccountRegisterMockMvc.perform(get("/api/account-registers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(accountRegister.getId().intValue())))
            .andExpect(jsonPath("$.[*].registerCode").value(hasItem(DEFAULT_REGISTER_CODE)))
            .andExpect(jsonPath("$.[*].accountName").value(hasItem(DEFAULT_ACCOUNT_NAME)))
            .andExpect(jsonPath("$.[*].accountType").value(hasItem(DEFAULT_ACCOUNT_TYPE)))
            .andExpect(jsonPath("$.[*].accountAngle").value(hasItem(DEFAULT_ACCOUNT_ANGLE.booleanValue())))
            .andExpect(jsonPath("$.[*].kseiClientCode").value(hasItem(DEFAULT_KSEI_CLIENT_CODE)))
            .andExpect(jsonPath("$.[*].kseiSubrek").value(hasItem(DEFAULT_KSEI_SUBREK)))
            .andExpect(jsonPath("$.[*].createSystemDate").value(hasItem(DEFAULT_CREATE_SYSTEM_DATE.toString())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(sameInstant(DEFAULT_CREATE_DATE))))
            .andExpect(jsonPath("$.[*].createUserId").value(hasItem(DEFAULT_CREATE_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].lastModificationSystemDate").value(hasItem(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModificationDate").value(hasItem(sameInstant(DEFAULT_LAST_MODIFICATION_DATE))))
            .andExpect(jsonPath("$.[*].lastModificationUserId").value(hasItem(DEFAULT_LAST_MODIFICATION_USER_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getAccountRegister() throws Exception {
        // Initialize the database
        accountRegisterRepository.saveAndFlush(accountRegister);

        // Get the accountRegister
        restAccountRegisterMockMvc.perform(get("/api/account-registers/{id}", accountRegister.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(accountRegister.getId().intValue()))
            .andExpect(jsonPath("$.registerCode").value(DEFAULT_REGISTER_CODE))
            .andExpect(jsonPath("$.accountName").value(DEFAULT_ACCOUNT_NAME))
            .andExpect(jsonPath("$.accountType").value(DEFAULT_ACCOUNT_TYPE))
            .andExpect(jsonPath("$.accountAngle").value(DEFAULT_ACCOUNT_ANGLE.booleanValue()))
            .andExpect(jsonPath("$.kseiClientCode").value(DEFAULT_KSEI_CLIENT_CODE))
            .andExpect(jsonPath("$.kseiSubrek").value(DEFAULT_KSEI_SUBREK))
            .andExpect(jsonPath("$.createSystemDate").value(DEFAULT_CREATE_SYSTEM_DATE.toString()))
            .andExpect(jsonPath("$.createDate").value(sameInstant(DEFAULT_CREATE_DATE)))
            .andExpect(jsonPath("$.createUserId").value(DEFAULT_CREATE_USER_ID.intValue()))
            .andExpect(jsonPath("$.lastModificationSystemDate").value(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE.toString()))
            .andExpect(jsonPath("$.lastModificationDate").value(sameInstant(DEFAULT_LAST_MODIFICATION_DATE)))
            .andExpect(jsonPath("$.lastModificationUserId").value(DEFAULT_LAST_MODIFICATION_USER_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAccountRegister() throws Exception {
        // Get the accountRegister
        restAccountRegisterMockMvc.perform(get("/api/account-registers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAccountRegister() throws Exception {
        // Initialize the database
        accountRegisterRepository.saveAndFlush(accountRegister);

        int databaseSizeBeforeUpdate = accountRegisterRepository.findAll().size();

        // Update the accountRegister
        AccountRegister updatedAccountRegister = accountRegisterRepository.findById(accountRegister.getId()).get();
        // Disconnect from session so that the updates on updatedAccountRegister are not directly saved in db
        em.detach(updatedAccountRegister);
        updatedAccountRegister
            .registerCode(UPDATED_REGISTER_CODE)
            .accountName(UPDATED_ACCOUNT_NAME)
            .accountType(UPDATED_ACCOUNT_TYPE)
            .accountAngle(UPDATED_ACCOUNT_ANGLE)
            .kseiClientCode(UPDATED_KSEI_CLIENT_CODE)
            .kseiSubrek(UPDATED_KSEI_SUBREK)
            .createSystemDate(UPDATED_CREATE_SYSTEM_DATE)
            .createDate(UPDATED_CREATE_DATE)
            .createUserId(UPDATED_CREATE_USER_ID)
            .lastModificationSystemDate(UPDATED_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(UPDATED_LAST_MODIFICATION_DATE)
            .lastModificationUserId(UPDATED_LAST_MODIFICATION_USER_ID);
        AccountRegisterDTO accountRegisterDTO = accountRegisterMapper.toDto(updatedAccountRegister);

        restAccountRegisterMockMvc.perform(put("/api/account-registers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountRegisterDTO)))
            .andExpect(status().isOk());

        // Validate the AccountRegister in the database
        List<AccountRegister> accountRegisterList = accountRegisterRepository.findAll();
        assertThat(accountRegisterList).hasSize(databaseSizeBeforeUpdate);
        AccountRegister testAccountRegister = accountRegisterList.get(accountRegisterList.size() - 1);
        assertThat(testAccountRegister.getRegisterCode()).isEqualTo(UPDATED_REGISTER_CODE);
        assertThat(testAccountRegister.getAccountName()).isEqualTo(UPDATED_ACCOUNT_NAME);
        assertThat(testAccountRegister.getAccountType()).isEqualTo(UPDATED_ACCOUNT_TYPE);
        assertThat(testAccountRegister.isAccountAngle()).isEqualTo(UPDATED_ACCOUNT_ANGLE);
        assertThat(testAccountRegister.getKseiClientCode()).isEqualTo(UPDATED_KSEI_CLIENT_CODE);
        assertThat(testAccountRegister.getKseiSubrek()).isEqualTo(UPDATED_KSEI_SUBREK);
        assertThat(testAccountRegister.getCreateSystemDate()).isEqualTo(UPDATED_CREATE_SYSTEM_DATE);
        assertThat(testAccountRegister.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testAccountRegister.getCreateUserId()).isEqualTo(UPDATED_CREATE_USER_ID);
        assertThat(testAccountRegister.getLastModificationSystemDate()).isEqualTo(UPDATED_LAST_MODIFICATION_SYSTEM_DATE);
        assertThat(testAccountRegister.getLastModificationDate()).isEqualTo(UPDATED_LAST_MODIFICATION_DATE);
        assertThat(testAccountRegister.getLastModificationUserId()).isEqualTo(UPDATED_LAST_MODIFICATION_USER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingAccountRegister() throws Exception {
        int databaseSizeBeforeUpdate = accountRegisterRepository.findAll().size();

        // Create the AccountRegister
        AccountRegisterDTO accountRegisterDTO = accountRegisterMapper.toDto(accountRegister);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAccountRegisterMockMvc.perform(put("/api/account-registers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountRegisterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AccountRegister in the database
        List<AccountRegister> accountRegisterList = accountRegisterRepository.findAll();
        assertThat(accountRegisterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAccountRegister() throws Exception {
        // Initialize the database
        accountRegisterRepository.saveAndFlush(accountRegister);

        int databaseSizeBeforeDelete = accountRegisterRepository.findAll().size();

        // Delete the accountRegister
        restAccountRegisterMockMvc.perform(delete("/api/account-registers/{id}", accountRegister.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AccountRegister> accountRegisterList = accountRegisterRepository.findAll();
        assertThat(accountRegisterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
