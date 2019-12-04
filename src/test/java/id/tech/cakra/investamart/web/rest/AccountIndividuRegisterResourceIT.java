package id.tech.cakra.investamart.web.rest;

import id.tech.cakra.investamart.InvestaregistersvcApp;
import id.tech.cakra.investamart.domain.AccountIndividuRegister;
import id.tech.cakra.investamart.repository.AccountIndividuRegisterRepository;
import id.tech.cakra.investamart.service.AccountIndividuRegisterService;
import id.tech.cakra.investamart.service.dto.AccountIndividuRegisterDTO;
import id.tech.cakra.investamart.service.mapper.AccountIndividuRegisterMapper;
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
 * Integration tests for the {@link AccountIndividuRegisterResource} REST controller.
 */
@SpringBootTest(classes = InvestaregistersvcApp.class)
public class AccountIndividuRegisterResourceIT {

    private static final String DEFAULT_SID = "AAAAAAAAAA";
    private static final String UPDATED_SID = "BBBBBBBBBB";

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_NATIONALITY_ID = 1L;
    private static final Long UPDATED_NATIONALITY_ID = 2L;

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

    private static final String DEFAULT_BIRTH_PLACE = "AAAAAAAAAA";
    private static final String UPDATED_BIRTH_PLACE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_BIRTH_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_BIRTH_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SEX = "A";
    private static final String UPDATED_SEX = "B";

    private static final String DEFAULT_MARITAL_STATUS = "A";
    private static final String UPDATED_MARITAL_STATUS = "B";

    private static final String DEFAULT_SPOUSE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SPOUSE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_HEIR = "AAAAAAAAAA";
    private static final String UPDATED_HEIR = "BBBBBBBBBB";

    private static final String DEFAULT_HEIR_RALATION = "AAAAAAAAAA";
    private static final String UPDATED_HEIR_RALATION = "BBBBBBBBBB";

    private static final String DEFAULT_EDUCATION_BACKGROUND = "A";
    private static final String UPDATED_EDUCATION_BACKGROUND = "B";

    private static final String DEFAULT_OCCUPATION = "A";
    private static final String UPDATED_OCCUPATION = "B";

    private static final String DEFAULT_NATURE_OF_BUSINESS = "AAAAAAAAAA";
    private static final String UPDATED_NATURE_OF_BUSINESS = "BBBBBBBBBB";

    private static final Double DEFAULT_ANNUAL_INCOME = 1D;
    private static final Double UPDATED_ANNUAL_INCOME = 2D;

    private static final String DEFAULT_FUND_SOURCE = "AAAAAAAAAA";
    private static final String UPDATED_FUND_SOURCE = "BBBBBBBBBB";

    private static final String DEFAULT_FUND_SOURCE_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_FUND_SOURCE_TEXT = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_INVESTMENT_OBJECTIVE = "AAAAAAAAAA";
    private static final String UPDATED_INVESTMENT_OBJECTIVE = "BBBBBBBBBB";

    private static final String DEFAULT_MOTHER_MAIDEN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MOTHER_MAIDEN_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DIRECT_SID = "AAAAAAAAAA";
    private static final String UPDATED_DIRECT_SID = "BBBBBBBBBB";

    private static final String DEFAULT_ASSET_OWNER = "A";
    private static final String UPDATED_ASSET_OWNER = "B";

    private static final LocalDate DEFAULT_AUTH_PERSON_KTP_REG_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_AUTH_PERSON_KTP_REG_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_TAX_ID = 1L;
    private static final Long UPDATED_TAX_ID = 2L;

    private static final String DEFAULT_IMAGE_KTP = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_KTP = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_NPWP = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_NPWP = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_SELFIE_KTP = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_SELFIE_KTP = "BBBBBBBBBB";

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
    private AccountIndividuRegisterRepository accountIndividuRegisterRepository;

    @Autowired
    private AccountIndividuRegisterMapper accountIndividuRegisterMapper;

    @Autowired
    private AccountIndividuRegisterService accountIndividuRegisterService;

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

    private MockMvc restAccountIndividuRegisterMockMvc;

    private AccountIndividuRegister accountIndividuRegister;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AccountIndividuRegisterResource accountIndividuRegisterResource = new AccountIndividuRegisterResource(accountIndividuRegisterService);
        this.restAccountIndividuRegisterMockMvc = MockMvcBuilders.standaloneSetup(accountIndividuRegisterResource)
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
    public static AccountIndividuRegister createEntity(EntityManager em) {
        AccountIndividuRegister accountIndividuRegister = new AccountIndividuRegister()
            .sid(DEFAULT_SID)
            .firstName(DEFAULT_FIRST_NAME)
            .middleName(DEFAULT_MIDDLE_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .nationalityId(DEFAULT_NATIONALITY_ID)
            .ktp(DEFAULT_KTP)
            .ktpExpDate(DEFAULT_KTP_EXP_DATE)
            .npwp(DEFAULT_NPWP)
            .npwpRegDate(DEFAULT_NPWP_REG_DATE)
            .passport(DEFAULT_PASSPORT)
            .passportExpDate(DEFAULT_PASSPORT_EXP_DATE)
            .kitas(DEFAULT_KITAS)
            .kitasExpDate(DEFAULT_KITAS_EXP_DATE)
            .birthPlace(DEFAULT_BIRTH_PLACE)
            .birthDate(DEFAULT_BIRTH_DATE)
            .sex(DEFAULT_SEX)
            .maritalStatus(DEFAULT_MARITAL_STATUS)
            .spouseName(DEFAULT_SPOUSE_NAME)
            .heir(DEFAULT_HEIR)
            .heirRalation(DEFAULT_HEIR_RALATION)
            .educationBackground(DEFAULT_EDUCATION_BACKGROUND)
            .occupation(DEFAULT_OCCUPATION)
            .natureOfBusiness(DEFAULT_NATURE_OF_BUSINESS)
            .annualIncome(DEFAULT_ANNUAL_INCOME)
            .fundSource(DEFAULT_FUND_SOURCE)
            .fundSourceText(DEFAULT_FUND_SOURCE_TEXT)
            .description(DEFAULT_DESCRIPTION)
            .investmentObjective(DEFAULT_INVESTMENT_OBJECTIVE)
            .motherMaidenName(DEFAULT_MOTHER_MAIDEN_NAME)
            .directSid(DEFAULT_DIRECT_SID)
            .assetOwner(DEFAULT_ASSET_OWNER)
            .authPersonKtpRegDate(DEFAULT_AUTH_PERSON_KTP_REG_DATE)
            .taxId(DEFAULT_TAX_ID)
            .imageKtp(DEFAULT_IMAGE_KTP)
            .imageNpwp(DEFAULT_IMAGE_NPWP)
            .imageSelfieKtp(DEFAULT_IMAGE_SELFIE_KTP)
            .createSystemDate(DEFAULT_CREATE_SYSTEM_DATE)
            .createDate(DEFAULT_CREATE_DATE)
            .createUserId(DEFAULT_CREATE_USER_ID)
            .lastModificationSystemDate(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(DEFAULT_LAST_MODIFICATION_DATE)
            .lastModificationUserId(DEFAULT_LAST_MODIFICATION_USER_ID);
        return accountIndividuRegister;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AccountIndividuRegister createUpdatedEntity(EntityManager em) {
        AccountIndividuRegister accountIndividuRegister = new AccountIndividuRegister()
            .sid(UPDATED_SID)
            .firstName(UPDATED_FIRST_NAME)
            .middleName(UPDATED_MIDDLE_NAME)
            .lastName(UPDATED_LAST_NAME)
            .nationalityId(UPDATED_NATIONALITY_ID)
            .ktp(UPDATED_KTP)
            .ktpExpDate(UPDATED_KTP_EXP_DATE)
            .npwp(UPDATED_NPWP)
            .npwpRegDate(UPDATED_NPWP_REG_DATE)
            .passport(UPDATED_PASSPORT)
            .passportExpDate(UPDATED_PASSPORT_EXP_DATE)
            .kitas(UPDATED_KITAS)
            .kitasExpDate(UPDATED_KITAS_EXP_DATE)
            .birthPlace(UPDATED_BIRTH_PLACE)
            .birthDate(UPDATED_BIRTH_DATE)
            .sex(UPDATED_SEX)
            .maritalStatus(UPDATED_MARITAL_STATUS)
            .spouseName(UPDATED_SPOUSE_NAME)
            .heir(UPDATED_HEIR)
            .heirRalation(UPDATED_HEIR_RALATION)
            .educationBackground(UPDATED_EDUCATION_BACKGROUND)
            .occupation(UPDATED_OCCUPATION)
            .natureOfBusiness(UPDATED_NATURE_OF_BUSINESS)
            .annualIncome(UPDATED_ANNUAL_INCOME)
            .fundSource(UPDATED_FUND_SOURCE)
            .fundSourceText(UPDATED_FUND_SOURCE_TEXT)
            .description(UPDATED_DESCRIPTION)
            .investmentObjective(UPDATED_INVESTMENT_OBJECTIVE)
            .motherMaidenName(UPDATED_MOTHER_MAIDEN_NAME)
            .directSid(UPDATED_DIRECT_SID)
            .assetOwner(UPDATED_ASSET_OWNER)
            .authPersonKtpRegDate(UPDATED_AUTH_PERSON_KTP_REG_DATE)
            .taxId(UPDATED_TAX_ID)
            .imageKtp(UPDATED_IMAGE_KTP)
            .imageNpwp(UPDATED_IMAGE_NPWP)
            .imageSelfieKtp(UPDATED_IMAGE_SELFIE_KTP)
            .createSystemDate(UPDATED_CREATE_SYSTEM_DATE)
            .createDate(UPDATED_CREATE_DATE)
            .createUserId(UPDATED_CREATE_USER_ID)
            .lastModificationSystemDate(UPDATED_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(UPDATED_LAST_MODIFICATION_DATE)
            .lastModificationUserId(UPDATED_LAST_MODIFICATION_USER_ID);
        return accountIndividuRegister;
    }

    @BeforeEach
    public void initTest() {
        accountIndividuRegister = createEntity(em);
    }

    @Test
    @Transactional
    public void createAccountIndividuRegister() throws Exception {
        int databaseSizeBeforeCreate = accountIndividuRegisterRepository.findAll().size();

        // Create the AccountIndividuRegister
        AccountIndividuRegisterDTO accountIndividuRegisterDTO = accountIndividuRegisterMapper.toDto(accountIndividuRegister);
        restAccountIndividuRegisterMockMvc.perform(post("/api/account-individu-registers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountIndividuRegisterDTO)))
            .andExpect(status().isCreated());

        // Validate the AccountIndividuRegister in the database
        List<AccountIndividuRegister> accountIndividuRegisterList = accountIndividuRegisterRepository.findAll();
        assertThat(accountIndividuRegisterList).hasSize(databaseSizeBeforeCreate + 1);
        AccountIndividuRegister testAccountIndividuRegister = accountIndividuRegisterList.get(accountIndividuRegisterList.size() - 1);
        assertThat(testAccountIndividuRegister.getSid()).isEqualTo(DEFAULT_SID);
        assertThat(testAccountIndividuRegister.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testAccountIndividuRegister.getMiddleName()).isEqualTo(DEFAULT_MIDDLE_NAME);
        assertThat(testAccountIndividuRegister.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testAccountIndividuRegister.getNationalityId()).isEqualTo(DEFAULT_NATIONALITY_ID);
        assertThat(testAccountIndividuRegister.getKtp()).isEqualTo(DEFAULT_KTP);
        assertThat(testAccountIndividuRegister.getKtpExpDate()).isEqualTo(DEFAULT_KTP_EXP_DATE);
        assertThat(testAccountIndividuRegister.getNpwp()).isEqualTo(DEFAULT_NPWP);
        assertThat(testAccountIndividuRegister.getNpwpRegDate()).isEqualTo(DEFAULT_NPWP_REG_DATE);
        assertThat(testAccountIndividuRegister.getPassport()).isEqualTo(DEFAULT_PASSPORT);
        assertThat(testAccountIndividuRegister.getPassportExpDate()).isEqualTo(DEFAULT_PASSPORT_EXP_DATE);
        assertThat(testAccountIndividuRegister.getKitas()).isEqualTo(DEFAULT_KITAS);
        assertThat(testAccountIndividuRegister.getKitasExpDate()).isEqualTo(DEFAULT_KITAS_EXP_DATE);
        assertThat(testAccountIndividuRegister.getBirthPlace()).isEqualTo(DEFAULT_BIRTH_PLACE);
        assertThat(testAccountIndividuRegister.getBirthDate()).isEqualTo(DEFAULT_BIRTH_DATE);
        assertThat(testAccountIndividuRegister.getSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testAccountIndividuRegister.getMaritalStatus()).isEqualTo(DEFAULT_MARITAL_STATUS);
        assertThat(testAccountIndividuRegister.getSpouseName()).isEqualTo(DEFAULT_SPOUSE_NAME);
        assertThat(testAccountIndividuRegister.getHeir()).isEqualTo(DEFAULT_HEIR);
        assertThat(testAccountIndividuRegister.getHeirRalation()).isEqualTo(DEFAULT_HEIR_RALATION);
        assertThat(testAccountIndividuRegister.getEducationBackground()).isEqualTo(DEFAULT_EDUCATION_BACKGROUND);
        assertThat(testAccountIndividuRegister.getOccupation()).isEqualTo(DEFAULT_OCCUPATION);
        assertThat(testAccountIndividuRegister.getNatureOfBusiness()).isEqualTo(DEFAULT_NATURE_OF_BUSINESS);
        assertThat(testAccountIndividuRegister.getAnnualIncome()).isEqualTo(DEFAULT_ANNUAL_INCOME);
        assertThat(testAccountIndividuRegister.getFundSource()).isEqualTo(DEFAULT_FUND_SOURCE);
        assertThat(testAccountIndividuRegister.getFundSourceText()).isEqualTo(DEFAULT_FUND_SOURCE_TEXT);
        assertThat(testAccountIndividuRegister.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testAccountIndividuRegister.getInvestmentObjective()).isEqualTo(DEFAULT_INVESTMENT_OBJECTIVE);
        assertThat(testAccountIndividuRegister.getMotherMaidenName()).isEqualTo(DEFAULT_MOTHER_MAIDEN_NAME);
        assertThat(testAccountIndividuRegister.getDirectSid()).isEqualTo(DEFAULT_DIRECT_SID);
        assertThat(testAccountIndividuRegister.getAssetOwner()).isEqualTo(DEFAULT_ASSET_OWNER);
        assertThat(testAccountIndividuRegister.getAuthPersonKtpRegDate()).isEqualTo(DEFAULT_AUTH_PERSON_KTP_REG_DATE);
        assertThat(testAccountIndividuRegister.getTaxId()).isEqualTo(DEFAULT_TAX_ID);
        assertThat(testAccountIndividuRegister.getImageKtp()).isEqualTo(DEFAULT_IMAGE_KTP);
        assertThat(testAccountIndividuRegister.getImageNpwp()).isEqualTo(DEFAULT_IMAGE_NPWP);
        assertThat(testAccountIndividuRegister.getImageSelfieKtp()).isEqualTo(DEFAULT_IMAGE_SELFIE_KTP);
        assertThat(testAccountIndividuRegister.getCreateSystemDate()).isEqualTo(DEFAULT_CREATE_SYSTEM_DATE);
        assertThat(testAccountIndividuRegister.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testAccountIndividuRegister.getCreateUserId()).isEqualTo(DEFAULT_CREATE_USER_ID);
        assertThat(testAccountIndividuRegister.getLastModificationSystemDate()).isEqualTo(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE);
        assertThat(testAccountIndividuRegister.getLastModificationDate()).isEqualTo(DEFAULT_LAST_MODIFICATION_DATE);
        assertThat(testAccountIndividuRegister.getLastModificationUserId()).isEqualTo(DEFAULT_LAST_MODIFICATION_USER_ID);
    }

    @Test
    @Transactional
    public void createAccountIndividuRegisterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = accountIndividuRegisterRepository.findAll().size();

        // Create the AccountIndividuRegister with an existing ID
        accountIndividuRegister.setId(1L);
        AccountIndividuRegisterDTO accountIndividuRegisterDTO = accountIndividuRegisterMapper.toDto(accountIndividuRegister);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAccountIndividuRegisterMockMvc.perform(post("/api/account-individu-registers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountIndividuRegisterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AccountIndividuRegister in the database
        List<AccountIndividuRegister> accountIndividuRegisterList = accountIndividuRegisterRepository.findAll();
        assertThat(accountIndividuRegisterList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAccountIndividuRegisters() throws Exception {
        // Initialize the database
        accountIndividuRegisterRepository.saveAndFlush(accountIndividuRegister);

        // Get all the accountIndividuRegisterList
        restAccountIndividuRegisterMockMvc.perform(get("/api/account-individu-registers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(accountIndividuRegister.getId().intValue())))
            .andExpect(jsonPath("$.[*].sid").value(hasItem(DEFAULT_SID)))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].middleName").value(hasItem(DEFAULT_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].nationalityId").value(hasItem(DEFAULT_NATIONALITY_ID.intValue())))
            .andExpect(jsonPath("$.[*].ktp").value(hasItem(DEFAULT_KTP)))
            .andExpect(jsonPath("$.[*].ktpExpDate").value(hasItem(DEFAULT_KTP_EXP_DATE.toString())))
            .andExpect(jsonPath("$.[*].npwp").value(hasItem(DEFAULT_NPWP)))
            .andExpect(jsonPath("$.[*].npwpRegDate").value(hasItem(DEFAULT_NPWP_REG_DATE.toString())))
            .andExpect(jsonPath("$.[*].passport").value(hasItem(DEFAULT_PASSPORT)))
            .andExpect(jsonPath("$.[*].passportExpDate").value(hasItem(DEFAULT_PASSPORT_EXP_DATE.toString())))
            .andExpect(jsonPath("$.[*].kitas").value(hasItem(DEFAULT_KITAS)))
            .andExpect(jsonPath("$.[*].kitasExpDate").value(hasItem(DEFAULT_KITAS_EXP_DATE.toString())))
            .andExpect(jsonPath("$.[*].birthPlace").value(hasItem(DEFAULT_BIRTH_PLACE)))
            .andExpect(jsonPath("$.[*].birthDate").value(hasItem(DEFAULT_BIRTH_DATE.toString())))
            .andExpect(jsonPath("$.[*].sex").value(hasItem(DEFAULT_SEX)))
            .andExpect(jsonPath("$.[*].maritalStatus").value(hasItem(DEFAULT_MARITAL_STATUS)))
            .andExpect(jsonPath("$.[*].spouseName").value(hasItem(DEFAULT_SPOUSE_NAME)))
            .andExpect(jsonPath("$.[*].heir").value(hasItem(DEFAULT_HEIR)))
            .andExpect(jsonPath("$.[*].heirRalation").value(hasItem(DEFAULT_HEIR_RALATION)))
            .andExpect(jsonPath("$.[*].educationBackground").value(hasItem(DEFAULT_EDUCATION_BACKGROUND)))
            .andExpect(jsonPath("$.[*].occupation").value(hasItem(DEFAULT_OCCUPATION)))
            .andExpect(jsonPath("$.[*].natureOfBusiness").value(hasItem(DEFAULT_NATURE_OF_BUSINESS)))
            .andExpect(jsonPath("$.[*].annualIncome").value(hasItem(DEFAULT_ANNUAL_INCOME.doubleValue())))
            .andExpect(jsonPath("$.[*].fundSource").value(hasItem(DEFAULT_FUND_SOURCE)))
            .andExpect(jsonPath("$.[*].fundSourceText").value(hasItem(DEFAULT_FUND_SOURCE_TEXT)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].investmentObjective").value(hasItem(DEFAULT_INVESTMENT_OBJECTIVE)))
            .andExpect(jsonPath("$.[*].motherMaidenName").value(hasItem(DEFAULT_MOTHER_MAIDEN_NAME)))
            .andExpect(jsonPath("$.[*].directSid").value(hasItem(DEFAULT_DIRECT_SID)))
            .andExpect(jsonPath("$.[*].assetOwner").value(hasItem(DEFAULT_ASSET_OWNER)))
            .andExpect(jsonPath("$.[*].authPersonKtpRegDate").value(hasItem(DEFAULT_AUTH_PERSON_KTP_REG_DATE.toString())))
            .andExpect(jsonPath("$.[*].taxId").value(hasItem(DEFAULT_TAX_ID.intValue())))
            .andExpect(jsonPath("$.[*].imageKtp").value(hasItem(DEFAULT_IMAGE_KTP)))
            .andExpect(jsonPath("$.[*].imageNpwp").value(hasItem(DEFAULT_IMAGE_NPWP)))
            .andExpect(jsonPath("$.[*].imageSelfieKtp").value(hasItem(DEFAULT_IMAGE_SELFIE_KTP)))
            .andExpect(jsonPath("$.[*].createSystemDate").value(hasItem(DEFAULT_CREATE_SYSTEM_DATE.toString())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(sameInstant(DEFAULT_CREATE_DATE))))
            .andExpect(jsonPath("$.[*].createUserId").value(hasItem(DEFAULT_CREATE_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].lastModificationSystemDate").value(hasItem(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModificationDate").value(hasItem(sameInstant(DEFAULT_LAST_MODIFICATION_DATE))))
            .andExpect(jsonPath("$.[*].lastModificationUserId").value(hasItem(DEFAULT_LAST_MODIFICATION_USER_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getAccountIndividuRegister() throws Exception {
        // Initialize the database
        accountIndividuRegisterRepository.saveAndFlush(accountIndividuRegister);

        // Get the accountIndividuRegister
        restAccountIndividuRegisterMockMvc.perform(get("/api/account-individu-registers/{id}", accountIndividuRegister.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(accountIndividuRegister.getId().intValue()))
            .andExpect(jsonPath("$.sid").value(DEFAULT_SID))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.middleName").value(DEFAULT_MIDDLE_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.nationalityId").value(DEFAULT_NATIONALITY_ID.intValue()))
            .andExpect(jsonPath("$.ktp").value(DEFAULT_KTP))
            .andExpect(jsonPath("$.ktpExpDate").value(DEFAULT_KTP_EXP_DATE.toString()))
            .andExpect(jsonPath("$.npwp").value(DEFAULT_NPWP))
            .andExpect(jsonPath("$.npwpRegDate").value(DEFAULT_NPWP_REG_DATE.toString()))
            .andExpect(jsonPath("$.passport").value(DEFAULT_PASSPORT))
            .andExpect(jsonPath("$.passportExpDate").value(DEFAULT_PASSPORT_EXP_DATE.toString()))
            .andExpect(jsonPath("$.kitas").value(DEFAULT_KITAS))
            .andExpect(jsonPath("$.kitasExpDate").value(DEFAULT_KITAS_EXP_DATE.toString()))
            .andExpect(jsonPath("$.birthPlace").value(DEFAULT_BIRTH_PLACE))
            .andExpect(jsonPath("$.birthDate").value(DEFAULT_BIRTH_DATE.toString()))
            .andExpect(jsonPath("$.sex").value(DEFAULT_SEX))
            .andExpect(jsonPath("$.maritalStatus").value(DEFAULT_MARITAL_STATUS))
            .andExpect(jsonPath("$.spouseName").value(DEFAULT_SPOUSE_NAME))
            .andExpect(jsonPath("$.heir").value(DEFAULT_HEIR))
            .andExpect(jsonPath("$.heirRalation").value(DEFAULT_HEIR_RALATION))
            .andExpect(jsonPath("$.educationBackground").value(DEFAULT_EDUCATION_BACKGROUND))
            .andExpect(jsonPath("$.occupation").value(DEFAULT_OCCUPATION))
            .andExpect(jsonPath("$.natureOfBusiness").value(DEFAULT_NATURE_OF_BUSINESS))
            .andExpect(jsonPath("$.annualIncome").value(DEFAULT_ANNUAL_INCOME.doubleValue()))
            .andExpect(jsonPath("$.fundSource").value(DEFAULT_FUND_SOURCE))
            .andExpect(jsonPath("$.fundSourceText").value(DEFAULT_FUND_SOURCE_TEXT))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.investmentObjective").value(DEFAULT_INVESTMENT_OBJECTIVE))
            .andExpect(jsonPath("$.motherMaidenName").value(DEFAULT_MOTHER_MAIDEN_NAME))
            .andExpect(jsonPath("$.directSid").value(DEFAULT_DIRECT_SID))
            .andExpect(jsonPath("$.assetOwner").value(DEFAULT_ASSET_OWNER))
            .andExpect(jsonPath("$.authPersonKtpRegDate").value(DEFAULT_AUTH_PERSON_KTP_REG_DATE.toString()))
            .andExpect(jsonPath("$.taxId").value(DEFAULT_TAX_ID.intValue()))
            .andExpect(jsonPath("$.imageKtp").value(DEFAULT_IMAGE_KTP))
            .andExpect(jsonPath("$.imageNpwp").value(DEFAULT_IMAGE_NPWP))
            .andExpect(jsonPath("$.imageSelfieKtp").value(DEFAULT_IMAGE_SELFIE_KTP))
            .andExpect(jsonPath("$.createSystemDate").value(DEFAULT_CREATE_SYSTEM_DATE.toString()))
            .andExpect(jsonPath("$.createDate").value(sameInstant(DEFAULT_CREATE_DATE)))
            .andExpect(jsonPath("$.createUserId").value(DEFAULT_CREATE_USER_ID.intValue()))
            .andExpect(jsonPath("$.lastModificationSystemDate").value(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE.toString()))
            .andExpect(jsonPath("$.lastModificationDate").value(sameInstant(DEFAULT_LAST_MODIFICATION_DATE)))
            .andExpect(jsonPath("$.lastModificationUserId").value(DEFAULT_LAST_MODIFICATION_USER_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAccountIndividuRegister() throws Exception {
        // Get the accountIndividuRegister
        restAccountIndividuRegisterMockMvc.perform(get("/api/account-individu-registers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAccountIndividuRegister() throws Exception {
        // Initialize the database
        accountIndividuRegisterRepository.saveAndFlush(accountIndividuRegister);

        int databaseSizeBeforeUpdate = accountIndividuRegisterRepository.findAll().size();

        // Update the accountIndividuRegister
        AccountIndividuRegister updatedAccountIndividuRegister = accountIndividuRegisterRepository.findById(accountIndividuRegister.getId()).get();
        // Disconnect from session so that the updates on updatedAccountIndividuRegister are not directly saved in db
        em.detach(updatedAccountIndividuRegister);
        updatedAccountIndividuRegister
            .sid(UPDATED_SID)
            .firstName(UPDATED_FIRST_NAME)
            .middleName(UPDATED_MIDDLE_NAME)
            .lastName(UPDATED_LAST_NAME)
            .nationalityId(UPDATED_NATIONALITY_ID)
            .ktp(UPDATED_KTP)
            .ktpExpDate(UPDATED_KTP_EXP_DATE)
            .npwp(UPDATED_NPWP)
            .npwpRegDate(UPDATED_NPWP_REG_DATE)
            .passport(UPDATED_PASSPORT)
            .passportExpDate(UPDATED_PASSPORT_EXP_DATE)
            .kitas(UPDATED_KITAS)
            .kitasExpDate(UPDATED_KITAS_EXP_DATE)
            .birthPlace(UPDATED_BIRTH_PLACE)
            .birthDate(UPDATED_BIRTH_DATE)
            .sex(UPDATED_SEX)
            .maritalStatus(UPDATED_MARITAL_STATUS)
            .spouseName(UPDATED_SPOUSE_NAME)
            .heir(UPDATED_HEIR)
            .heirRalation(UPDATED_HEIR_RALATION)
            .educationBackground(UPDATED_EDUCATION_BACKGROUND)
            .occupation(UPDATED_OCCUPATION)
            .natureOfBusiness(UPDATED_NATURE_OF_BUSINESS)
            .annualIncome(UPDATED_ANNUAL_INCOME)
            .fundSource(UPDATED_FUND_SOURCE)
            .fundSourceText(UPDATED_FUND_SOURCE_TEXT)
            .description(UPDATED_DESCRIPTION)
            .investmentObjective(UPDATED_INVESTMENT_OBJECTIVE)
            .motherMaidenName(UPDATED_MOTHER_MAIDEN_NAME)
            .directSid(UPDATED_DIRECT_SID)
            .assetOwner(UPDATED_ASSET_OWNER)
            .authPersonKtpRegDate(UPDATED_AUTH_PERSON_KTP_REG_DATE)
            .taxId(UPDATED_TAX_ID)
            .imageKtp(UPDATED_IMAGE_KTP)
            .imageNpwp(UPDATED_IMAGE_NPWP)
            .imageSelfieKtp(UPDATED_IMAGE_SELFIE_KTP)
            .createSystemDate(UPDATED_CREATE_SYSTEM_DATE)
            .createDate(UPDATED_CREATE_DATE)
            .createUserId(UPDATED_CREATE_USER_ID)
            .lastModificationSystemDate(UPDATED_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(UPDATED_LAST_MODIFICATION_DATE)
            .lastModificationUserId(UPDATED_LAST_MODIFICATION_USER_ID);
        AccountIndividuRegisterDTO accountIndividuRegisterDTO = accountIndividuRegisterMapper.toDto(updatedAccountIndividuRegister);

        restAccountIndividuRegisterMockMvc.perform(put("/api/account-individu-registers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountIndividuRegisterDTO)))
            .andExpect(status().isOk());

        // Validate the AccountIndividuRegister in the database
        List<AccountIndividuRegister> accountIndividuRegisterList = accountIndividuRegisterRepository.findAll();
        assertThat(accountIndividuRegisterList).hasSize(databaseSizeBeforeUpdate);
        AccountIndividuRegister testAccountIndividuRegister = accountIndividuRegisterList.get(accountIndividuRegisterList.size() - 1);
        assertThat(testAccountIndividuRegister.getSid()).isEqualTo(UPDATED_SID);
        assertThat(testAccountIndividuRegister.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testAccountIndividuRegister.getMiddleName()).isEqualTo(UPDATED_MIDDLE_NAME);
        assertThat(testAccountIndividuRegister.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testAccountIndividuRegister.getNationalityId()).isEqualTo(UPDATED_NATIONALITY_ID);
        assertThat(testAccountIndividuRegister.getKtp()).isEqualTo(UPDATED_KTP);
        assertThat(testAccountIndividuRegister.getKtpExpDate()).isEqualTo(UPDATED_KTP_EXP_DATE);
        assertThat(testAccountIndividuRegister.getNpwp()).isEqualTo(UPDATED_NPWP);
        assertThat(testAccountIndividuRegister.getNpwpRegDate()).isEqualTo(UPDATED_NPWP_REG_DATE);
        assertThat(testAccountIndividuRegister.getPassport()).isEqualTo(UPDATED_PASSPORT);
        assertThat(testAccountIndividuRegister.getPassportExpDate()).isEqualTo(UPDATED_PASSPORT_EXP_DATE);
        assertThat(testAccountIndividuRegister.getKitas()).isEqualTo(UPDATED_KITAS);
        assertThat(testAccountIndividuRegister.getKitasExpDate()).isEqualTo(UPDATED_KITAS_EXP_DATE);
        assertThat(testAccountIndividuRegister.getBirthPlace()).isEqualTo(UPDATED_BIRTH_PLACE);
        assertThat(testAccountIndividuRegister.getBirthDate()).isEqualTo(UPDATED_BIRTH_DATE);
        assertThat(testAccountIndividuRegister.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testAccountIndividuRegister.getMaritalStatus()).isEqualTo(UPDATED_MARITAL_STATUS);
        assertThat(testAccountIndividuRegister.getSpouseName()).isEqualTo(UPDATED_SPOUSE_NAME);
        assertThat(testAccountIndividuRegister.getHeir()).isEqualTo(UPDATED_HEIR);
        assertThat(testAccountIndividuRegister.getHeirRalation()).isEqualTo(UPDATED_HEIR_RALATION);
        assertThat(testAccountIndividuRegister.getEducationBackground()).isEqualTo(UPDATED_EDUCATION_BACKGROUND);
        assertThat(testAccountIndividuRegister.getOccupation()).isEqualTo(UPDATED_OCCUPATION);
        assertThat(testAccountIndividuRegister.getNatureOfBusiness()).isEqualTo(UPDATED_NATURE_OF_BUSINESS);
        assertThat(testAccountIndividuRegister.getAnnualIncome()).isEqualTo(UPDATED_ANNUAL_INCOME);
        assertThat(testAccountIndividuRegister.getFundSource()).isEqualTo(UPDATED_FUND_SOURCE);
        assertThat(testAccountIndividuRegister.getFundSourceText()).isEqualTo(UPDATED_FUND_SOURCE_TEXT);
        assertThat(testAccountIndividuRegister.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testAccountIndividuRegister.getInvestmentObjective()).isEqualTo(UPDATED_INVESTMENT_OBJECTIVE);
        assertThat(testAccountIndividuRegister.getMotherMaidenName()).isEqualTo(UPDATED_MOTHER_MAIDEN_NAME);
        assertThat(testAccountIndividuRegister.getDirectSid()).isEqualTo(UPDATED_DIRECT_SID);
        assertThat(testAccountIndividuRegister.getAssetOwner()).isEqualTo(UPDATED_ASSET_OWNER);
        assertThat(testAccountIndividuRegister.getAuthPersonKtpRegDate()).isEqualTo(UPDATED_AUTH_PERSON_KTP_REG_DATE);
        assertThat(testAccountIndividuRegister.getTaxId()).isEqualTo(UPDATED_TAX_ID);
        assertThat(testAccountIndividuRegister.getImageKtp()).isEqualTo(UPDATED_IMAGE_KTP);
        assertThat(testAccountIndividuRegister.getImageNpwp()).isEqualTo(UPDATED_IMAGE_NPWP);
        assertThat(testAccountIndividuRegister.getImageSelfieKtp()).isEqualTo(UPDATED_IMAGE_SELFIE_KTP);
        assertThat(testAccountIndividuRegister.getCreateSystemDate()).isEqualTo(UPDATED_CREATE_SYSTEM_DATE);
        assertThat(testAccountIndividuRegister.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testAccountIndividuRegister.getCreateUserId()).isEqualTo(UPDATED_CREATE_USER_ID);
        assertThat(testAccountIndividuRegister.getLastModificationSystemDate()).isEqualTo(UPDATED_LAST_MODIFICATION_SYSTEM_DATE);
        assertThat(testAccountIndividuRegister.getLastModificationDate()).isEqualTo(UPDATED_LAST_MODIFICATION_DATE);
        assertThat(testAccountIndividuRegister.getLastModificationUserId()).isEqualTo(UPDATED_LAST_MODIFICATION_USER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingAccountIndividuRegister() throws Exception {
        int databaseSizeBeforeUpdate = accountIndividuRegisterRepository.findAll().size();

        // Create the AccountIndividuRegister
        AccountIndividuRegisterDTO accountIndividuRegisterDTO = accountIndividuRegisterMapper.toDto(accountIndividuRegister);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAccountIndividuRegisterMockMvc.perform(put("/api/account-individu-registers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountIndividuRegisterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AccountIndividuRegister in the database
        List<AccountIndividuRegister> accountIndividuRegisterList = accountIndividuRegisterRepository.findAll();
        assertThat(accountIndividuRegisterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAccountIndividuRegister() throws Exception {
        // Initialize the database
        accountIndividuRegisterRepository.saveAndFlush(accountIndividuRegister);

        int databaseSizeBeforeDelete = accountIndividuRegisterRepository.findAll().size();

        // Delete the accountIndividuRegister
        restAccountIndividuRegisterMockMvc.perform(delete("/api/account-individu-registers/{id}", accountIndividuRegister.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AccountIndividuRegister> accountIndividuRegisterList = accountIndividuRegisterRepository.findAll();
        assertThat(accountIndividuRegisterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
