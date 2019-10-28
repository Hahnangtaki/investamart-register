package id.tech.cakra.investamart.web.rest;

import id.tech.cakra.investamart.InvestaregistersvcApp;
import id.tech.cakra.investamart.domain.AccountAuthorizeRegister;
import id.tech.cakra.investamart.repository.AccountAuthorizeRegisterRepository;
import id.tech.cakra.investamart.service.AccountAuthorizeRegisterService;
import id.tech.cakra.investamart.service.dto.AccountAuthorizeRegisterDTO;
import id.tech.cakra.investamart.service.mapper.AccountAuthorizeRegisterMapper;
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
 * Integration tests for the {@link AccountAuthorizeRegisterResource} REST controller.
 */
@SpringBootTest(classes = InvestaregistersvcApp.class)
public class AccountAuthorizeRegisterResourceIT {

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_POSITION = "AAAAAAAAAA";
    private static final String UPDATED_POSITION = "BBBBBBBBBB";

    private static final String DEFAULT_KTP = "AAAAAAAAAA";
    private static final String UPDATED_KTP = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_KTP_EXP_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_KTP_EXP_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NPWP = "AAAAAAAAAA";
    private static final String UPDATED_NPWP = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NPWP_REG_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NPWP_REG_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_PASSPORT = "AAAAAAAAAA";
    private static final String UPDATED_PASSPORT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PASSPORT_EXP_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PASSPORT_EXP_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_KITAS = "AAAAAAAAAA";
    private static final String UPDATED_KITAS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_KITAS_EXP_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_KITAS_EXP_DATE = LocalDate.now(ZoneId.systemDefault());

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
    private AccountAuthorizeRegisterRepository accountAuthorizeRegisterRepository;

    @Autowired
    private AccountAuthorizeRegisterMapper accountAuthorizeRegisterMapper;

    @Autowired
    private AccountAuthorizeRegisterService accountAuthorizeRegisterService;

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

    private MockMvc restAccountAuthorizeRegisterMockMvc;

    private AccountAuthorizeRegister accountAuthorizeRegister;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AccountAuthorizeRegisterResource accountAuthorizeRegisterResource = new AccountAuthorizeRegisterResource(accountAuthorizeRegisterService);
        this.restAccountAuthorizeRegisterMockMvc = MockMvcBuilders.standaloneSetup(accountAuthorizeRegisterResource)
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
    public static AccountAuthorizeRegister createEntity(EntityManager em) {
        AccountAuthorizeRegister accountAuthorizeRegister = new AccountAuthorizeRegister()
            .firstName(DEFAULT_FIRST_NAME)
            .middleName(DEFAULT_MIDDLE_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .position(DEFAULT_POSITION)
            .ktp(DEFAULT_KTP)
            .ktpExpDate(DEFAULT_KTP_EXP_DATE)
            .npwp(DEFAULT_NPWP)
            .npwpRegDate(DEFAULT_NPWP_REG_DATE)
            .passport(DEFAULT_PASSPORT)
            .passportExpDate(DEFAULT_PASSPORT_EXP_DATE)
            .kitas(DEFAULT_KITAS)
            .kitasExpDate(DEFAULT_KITAS_EXP_DATE)
            .createSystemDate(DEFAULT_CREATE_SYSTEM_DATE)
            .createDate(DEFAULT_CREATE_DATE)
            .createUserId(DEFAULT_CREATE_USER_ID)
            .lastModificationSystemDate(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(DEFAULT_LAST_MODIFICATION_DATE)
            .lastModificationUserId(DEFAULT_LAST_MODIFICATION_USER_ID);
        return accountAuthorizeRegister;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AccountAuthorizeRegister createUpdatedEntity(EntityManager em) {
        AccountAuthorizeRegister accountAuthorizeRegister = new AccountAuthorizeRegister()
            .firstName(UPDATED_FIRST_NAME)
            .middleName(UPDATED_MIDDLE_NAME)
            .lastName(UPDATED_LAST_NAME)
            .position(UPDATED_POSITION)
            .ktp(UPDATED_KTP)
            .ktpExpDate(UPDATED_KTP_EXP_DATE)
            .npwp(UPDATED_NPWP)
            .npwpRegDate(UPDATED_NPWP_REG_DATE)
            .passport(UPDATED_PASSPORT)
            .passportExpDate(UPDATED_PASSPORT_EXP_DATE)
            .kitas(UPDATED_KITAS)
            .kitasExpDate(UPDATED_KITAS_EXP_DATE)
            .createSystemDate(UPDATED_CREATE_SYSTEM_DATE)
            .createDate(UPDATED_CREATE_DATE)
            .createUserId(UPDATED_CREATE_USER_ID)
            .lastModificationSystemDate(UPDATED_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(UPDATED_LAST_MODIFICATION_DATE)
            .lastModificationUserId(UPDATED_LAST_MODIFICATION_USER_ID);
        return accountAuthorizeRegister;
    }

    @BeforeEach
    public void initTest() {
        accountAuthorizeRegister = createEntity(em);
    }

    @Test
    @Transactional
    public void createAccountAuthorizeRegister() throws Exception {
        int databaseSizeBeforeCreate = accountAuthorizeRegisterRepository.findAll().size();

        // Create the AccountAuthorizeRegister
        AccountAuthorizeRegisterDTO accountAuthorizeRegisterDTO = accountAuthorizeRegisterMapper.toDto(accountAuthorizeRegister);
        restAccountAuthorizeRegisterMockMvc.perform(post("/api/account-authorize-registers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountAuthorizeRegisterDTO)))
            .andExpect(status().isCreated());

        // Validate the AccountAuthorizeRegister in the database
        List<AccountAuthorizeRegister> accountAuthorizeRegisterList = accountAuthorizeRegisterRepository.findAll();
        assertThat(accountAuthorizeRegisterList).hasSize(databaseSizeBeforeCreate + 1);
        AccountAuthorizeRegister testAccountAuthorizeRegister = accountAuthorizeRegisterList.get(accountAuthorizeRegisterList.size() - 1);
        assertThat(testAccountAuthorizeRegister.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testAccountAuthorizeRegister.getMiddleName()).isEqualTo(DEFAULT_MIDDLE_NAME);
        assertThat(testAccountAuthorizeRegister.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testAccountAuthorizeRegister.getPosition()).isEqualTo(DEFAULT_POSITION);
        assertThat(testAccountAuthorizeRegister.getKtp()).isEqualTo(DEFAULT_KTP);
        assertThat(testAccountAuthorizeRegister.getKtpExpDate()).isEqualTo(DEFAULT_KTP_EXP_DATE);
        assertThat(testAccountAuthorizeRegister.getNpwp()).isEqualTo(DEFAULT_NPWP);
        assertThat(testAccountAuthorizeRegister.getNpwpRegDate()).isEqualTo(DEFAULT_NPWP_REG_DATE);
        assertThat(testAccountAuthorizeRegister.getPassport()).isEqualTo(DEFAULT_PASSPORT);
        assertThat(testAccountAuthorizeRegister.getPassportExpDate()).isEqualTo(DEFAULT_PASSPORT_EXP_DATE);
        assertThat(testAccountAuthorizeRegister.getKitas()).isEqualTo(DEFAULT_KITAS);
        assertThat(testAccountAuthorizeRegister.getKitasExpDate()).isEqualTo(DEFAULT_KITAS_EXP_DATE);
        assertThat(testAccountAuthorizeRegister.getCreateSystemDate()).isEqualTo(DEFAULT_CREATE_SYSTEM_DATE);
        assertThat(testAccountAuthorizeRegister.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testAccountAuthorizeRegister.getCreateUserId()).isEqualTo(DEFAULT_CREATE_USER_ID);
        assertThat(testAccountAuthorizeRegister.getLastModificationSystemDate()).isEqualTo(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE);
        assertThat(testAccountAuthorizeRegister.getLastModificationDate()).isEqualTo(DEFAULT_LAST_MODIFICATION_DATE);
        assertThat(testAccountAuthorizeRegister.getLastModificationUserId()).isEqualTo(DEFAULT_LAST_MODIFICATION_USER_ID);
    }

    @Test
    @Transactional
    public void createAccountAuthorizeRegisterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = accountAuthorizeRegisterRepository.findAll().size();

        // Create the AccountAuthorizeRegister with an existing ID
        accountAuthorizeRegister.setId(1L);
        AccountAuthorizeRegisterDTO accountAuthorizeRegisterDTO = accountAuthorizeRegisterMapper.toDto(accountAuthorizeRegister);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAccountAuthorizeRegisterMockMvc.perform(post("/api/account-authorize-registers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountAuthorizeRegisterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AccountAuthorizeRegister in the database
        List<AccountAuthorizeRegister> accountAuthorizeRegisterList = accountAuthorizeRegisterRepository.findAll();
        assertThat(accountAuthorizeRegisterList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAccountAuthorizeRegisters() throws Exception {
        // Initialize the database
        accountAuthorizeRegisterRepository.saveAndFlush(accountAuthorizeRegister);

        // Get all the accountAuthorizeRegisterList
        restAccountAuthorizeRegisterMockMvc.perform(get("/api/account-authorize-registers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(accountAuthorizeRegister.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].middleName").value(hasItem(DEFAULT_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].position").value(hasItem(DEFAULT_POSITION)))
            .andExpect(jsonPath("$.[*].ktp").value(hasItem(DEFAULT_KTP)))
            .andExpect(jsonPath("$.[*].ktpExpDate").value(hasItem(DEFAULT_KTP_EXP_DATE.toString())))
            .andExpect(jsonPath("$.[*].npwp").value(hasItem(DEFAULT_NPWP)))
            .andExpect(jsonPath("$.[*].npwpRegDate").value(hasItem(DEFAULT_NPWP_REG_DATE.toString())))
            .andExpect(jsonPath("$.[*].passport").value(hasItem(DEFAULT_PASSPORT)))
            .andExpect(jsonPath("$.[*].passportExpDate").value(hasItem(DEFAULT_PASSPORT_EXP_DATE.toString())))
            .andExpect(jsonPath("$.[*].kitas").value(hasItem(DEFAULT_KITAS)))
            .andExpect(jsonPath("$.[*].kitasExpDate").value(hasItem(DEFAULT_KITAS_EXP_DATE.toString())))
            .andExpect(jsonPath("$.[*].createSystemDate").value(hasItem(DEFAULT_CREATE_SYSTEM_DATE.toString())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(sameInstant(DEFAULT_CREATE_DATE))))
            .andExpect(jsonPath("$.[*].createUserId").value(hasItem(DEFAULT_CREATE_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].lastModificationSystemDate").value(hasItem(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModificationDate").value(hasItem(sameInstant(DEFAULT_LAST_MODIFICATION_DATE))))
            .andExpect(jsonPath("$.[*].lastModificationUserId").value(hasItem(DEFAULT_LAST_MODIFICATION_USER_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getAccountAuthorizeRegister() throws Exception {
        // Initialize the database
        accountAuthorizeRegisterRepository.saveAndFlush(accountAuthorizeRegister);

        // Get the accountAuthorizeRegister
        restAccountAuthorizeRegisterMockMvc.perform(get("/api/account-authorize-registers/{id}", accountAuthorizeRegister.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(accountAuthorizeRegister.getId().intValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.middleName").value(DEFAULT_MIDDLE_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.position").value(DEFAULT_POSITION))
            .andExpect(jsonPath("$.ktp").value(DEFAULT_KTP))
            .andExpect(jsonPath("$.ktpExpDate").value(DEFAULT_KTP_EXP_DATE.toString()))
            .andExpect(jsonPath("$.npwp").value(DEFAULT_NPWP))
            .andExpect(jsonPath("$.npwpRegDate").value(DEFAULT_NPWP_REG_DATE.toString()))
            .andExpect(jsonPath("$.passport").value(DEFAULT_PASSPORT))
            .andExpect(jsonPath("$.passportExpDate").value(DEFAULT_PASSPORT_EXP_DATE.toString()))
            .andExpect(jsonPath("$.kitas").value(DEFAULT_KITAS))
            .andExpect(jsonPath("$.kitasExpDate").value(DEFAULT_KITAS_EXP_DATE.toString()))
            .andExpect(jsonPath("$.createSystemDate").value(DEFAULT_CREATE_SYSTEM_DATE.toString()))
            .andExpect(jsonPath("$.createDate").value(sameInstant(DEFAULT_CREATE_DATE)))
            .andExpect(jsonPath("$.createUserId").value(DEFAULT_CREATE_USER_ID.intValue()))
            .andExpect(jsonPath("$.lastModificationSystemDate").value(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE.toString()))
            .andExpect(jsonPath("$.lastModificationDate").value(sameInstant(DEFAULT_LAST_MODIFICATION_DATE)))
            .andExpect(jsonPath("$.lastModificationUserId").value(DEFAULT_LAST_MODIFICATION_USER_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAccountAuthorizeRegister() throws Exception {
        // Get the accountAuthorizeRegister
        restAccountAuthorizeRegisterMockMvc.perform(get("/api/account-authorize-registers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAccountAuthorizeRegister() throws Exception {
        // Initialize the database
        accountAuthorizeRegisterRepository.saveAndFlush(accountAuthorizeRegister);

        int databaseSizeBeforeUpdate = accountAuthorizeRegisterRepository.findAll().size();

        // Update the accountAuthorizeRegister
        AccountAuthorizeRegister updatedAccountAuthorizeRegister = accountAuthorizeRegisterRepository.findById(accountAuthorizeRegister.getId()).get();
        // Disconnect from session so that the updates on updatedAccountAuthorizeRegister are not directly saved in db
        em.detach(updatedAccountAuthorizeRegister);
        updatedAccountAuthorizeRegister
            .firstName(UPDATED_FIRST_NAME)
            .middleName(UPDATED_MIDDLE_NAME)
            .lastName(UPDATED_LAST_NAME)
            .position(UPDATED_POSITION)
            .ktp(UPDATED_KTP)
            .ktpExpDate(UPDATED_KTP_EXP_DATE)
            .npwp(UPDATED_NPWP)
            .npwpRegDate(UPDATED_NPWP_REG_DATE)
            .passport(UPDATED_PASSPORT)
            .passportExpDate(UPDATED_PASSPORT_EXP_DATE)
            .kitas(UPDATED_KITAS)
            .kitasExpDate(UPDATED_KITAS_EXP_DATE)
            .createSystemDate(UPDATED_CREATE_SYSTEM_DATE)
            .createDate(UPDATED_CREATE_DATE)
            .createUserId(UPDATED_CREATE_USER_ID)
            .lastModificationSystemDate(UPDATED_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(UPDATED_LAST_MODIFICATION_DATE)
            .lastModificationUserId(UPDATED_LAST_MODIFICATION_USER_ID);
        AccountAuthorizeRegisterDTO accountAuthorizeRegisterDTO = accountAuthorizeRegisterMapper.toDto(updatedAccountAuthorizeRegister);

        restAccountAuthorizeRegisterMockMvc.perform(put("/api/account-authorize-registers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountAuthorizeRegisterDTO)))
            .andExpect(status().isOk());

        // Validate the AccountAuthorizeRegister in the database
        List<AccountAuthorizeRegister> accountAuthorizeRegisterList = accountAuthorizeRegisterRepository.findAll();
        assertThat(accountAuthorizeRegisterList).hasSize(databaseSizeBeforeUpdate);
        AccountAuthorizeRegister testAccountAuthorizeRegister = accountAuthorizeRegisterList.get(accountAuthorizeRegisterList.size() - 1);
        assertThat(testAccountAuthorizeRegister.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testAccountAuthorizeRegister.getMiddleName()).isEqualTo(UPDATED_MIDDLE_NAME);
        assertThat(testAccountAuthorizeRegister.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testAccountAuthorizeRegister.getPosition()).isEqualTo(UPDATED_POSITION);
        assertThat(testAccountAuthorizeRegister.getKtp()).isEqualTo(UPDATED_KTP);
        assertThat(testAccountAuthorizeRegister.getKtpExpDate()).isEqualTo(UPDATED_KTP_EXP_DATE);
        assertThat(testAccountAuthorizeRegister.getNpwp()).isEqualTo(UPDATED_NPWP);
        assertThat(testAccountAuthorizeRegister.getNpwpRegDate()).isEqualTo(UPDATED_NPWP_REG_DATE);
        assertThat(testAccountAuthorizeRegister.getPassport()).isEqualTo(UPDATED_PASSPORT);
        assertThat(testAccountAuthorizeRegister.getPassportExpDate()).isEqualTo(UPDATED_PASSPORT_EXP_DATE);
        assertThat(testAccountAuthorizeRegister.getKitas()).isEqualTo(UPDATED_KITAS);
        assertThat(testAccountAuthorizeRegister.getKitasExpDate()).isEqualTo(UPDATED_KITAS_EXP_DATE);
        assertThat(testAccountAuthorizeRegister.getCreateSystemDate()).isEqualTo(UPDATED_CREATE_SYSTEM_DATE);
        assertThat(testAccountAuthorizeRegister.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testAccountAuthorizeRegister.getCreateUserId()).isEqualTo(UPDATED_CREATE_USER_ID);
        assertThat(testAccountAuthorizeRegister.getLastModificationSystemDate()).isEqualTo(UPDATED_LAST_MODIFICATION_SYSTEM_DATE);
        assertThat(testAccountAuthorizeRegister.getLastModificationDate()).isEqualTo(UPDATED_LAST_MODIFICATION_DATE);
        assertThat(testAccountAuthorizeRegister.getLastModificationUserId()).isEqualTo(UPDATED_LAST_MODIFICATION_USER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingAccountAuthorizeRegister() throws Exception {
        int databaseSizeBeforeUpdate = accountAuthorizeRegisterRepository.findAll().size();

        // Create the AccountAuthorizeRegister
        AccountAuthorizeRegisterDTO accountAuthorizeRegisterDTO = accountAuthorizeRegisterMapper.toDto(accountAuthorizeRegister);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAccountAuthorizeRegisterMockMvc.perform(put("/api/account-authorize-registers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountAuthorizeRegisterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AccountAuthorizeRegister in the database
        List<AccountAuthorizeRegister> accountAuthorizeRegisterList = accountAuthorizeRegisterRepository.findAll();
        assertThat(accountAuthorizeRegisterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAccountAuthorizeRegister() throws Exception {
        // Initialize the database
        accountAuthorizeRegisterRepository.saveAndFlush(accountAuthorizeRegister);

        int databaseSizeBeforeDelete = accountAuthorizeRegisterRepository.findAll().size();

        // Delete the accountAuthorizeRegister
        restAccountAuthorizeRegisterMockMvc.perform(delete("/api/account-authorize-registers/{id}", accountAuthorizeRegister.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AccountAuthorizeRegister> accountAuthorizeRegisterList = accountAuthorizeRegisterRepository.findAll();
        assertThat(accountAuthorizeRegisterList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountAuthorizeRegister.class);
        AccountAuthorizeRegister accountAuthorizeRegister1 = new AccountAuthorizeRegister();
        accountAuthorizeRegister1.setId(1L);
        AccountAuthorizeRegister accountAuthorizeRegister2 = new AccountAuthorizeRegister();
        accountAuthorizeRegister2.setId(accountAuthorizeRegister1.getId());
        assertThat(accountAuthorizeRegister1).isEqualTo(accountAuthorizeRegister2);
        accountAuthorizeRegister2.setId(2L);
        assertThat(accountAuthorizeRegister1).isNotEqualTo(accountAuthorizeRegister2);
        accountAuthorizeRegister1.setId(null);
        assertThat(accountAuthorizeRegister1).isNotEqualTo(accountAuthorizeRegister2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountAuthorizeRegisterDTO.class);
        AccountAuthorizeRegisterDTO accountAuthorizeRegisterDTO1 = new AccountAuthorizeRegisterDTO();
        accountAuthorizeRegisterDTO1.setId(1L);
        AccountAuthorizeRegisterDTO accountAuthorizeRegisterDTO2 = new AccountAuthorizeRegisterDTO();
        assertThat(accountAuthorizeRegisterDTO1).isNotEqualTo(accountAuthorizeRegisterDTO2);
        accountAuthorizeRegisterDTO2.setId(accountAuthorizeRegisterDTO1.getId());
        assertThat(accountAuthorizeRegisterDTO1).isEqualTo(accountAuthorizeRegisterDTO2);
        accountAuthorizeRegisterDTO2.setId(2L);
        assertThat(accountAuthorizeRegisterDTO1).isNotEqualTo(accountAuthorizeRegisterDTO2);
        accountAuthorizeRegisterDTO1.setId(null);
        assertThat(accountAuthorizeRegisterDTO1).isNotEqualTo(accountAuthorizeRegisterDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(accountAuthorizeRegisterMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(accountAuthorizeRegisterMapper.fromId(null)).isNull();
    }
}
