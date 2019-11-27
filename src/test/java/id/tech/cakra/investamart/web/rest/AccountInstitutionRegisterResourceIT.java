package id.tech.cakra.investamart.web.rest;

import id.tech.cakra.investamart.InvestaregistersvcApp;
import id.tech.cakra.investamart.domain.AccountInstitutionRegister;
import id.tech.cakra.investamart.repository.AccountInstitutionRegisterRepository;
import id.tech.cakra.investamart.service.AccountInstitutionRegisterService;
import id.tech.cakra.investamart.service.dto.AccountInstitutionRegisterDTO;
import id.tech.cakra.investamart.service.mapper.AccountInstitutionRegisterMapper;
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
 * Integration tests for the {@link AccountInstitutionRegisterResource} REST controller.
 */
@SpringBootTest(classes = InvestaregistersvcApp.class)
public class AccountInstitutionRegisterResourceIT {

    private static final String DEFAULT_SID = "AAAAAAAAAA";
    private static final String UPDATED_SID = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BIC_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BIC_CODE = "BBBBBBBBBB";

    private static final Long DEFAULT_LEGAL_DOMICILE_ID = 1L;
    private static final Long UPDATED_LEGAL_DOMICILE_ID = 2L;

    private static final String DEFAULT_NPWP = "AAAAAAAAAA";
    private static final String UPDATED_NPWP = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NPWP_REG_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NPWP_REG_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SKD = "AAAAAAAAAA";
    private static final String UPDATED_SKD = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_SKD_EXP_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SKD_EXP_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_COMPANY_ESTABLISH_PLCE = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_ESTABLISH_PLCE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_COMPANY_ESTABLISH_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_COMPANY_ESTABLISH_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_BUSINESS_TYPE = "A";
    private static final String UPDATED_BUSINESS_TYPE = "B";

    private static final String DEFAULT_COMPANY_CHRACTERISTIC = "A";
    private static final String UPDATED_COMPANY_CHRACTERISTIC = "B";

    private static final String DEFAULT_FUND_SOURCE = "AAAAAAAAAA";
    private static final String UPDATED_FUND_SOURCE = "BBBBBBBBBB";

    private static final String DEFAULT_FUND_SOURCE_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_FUND_SOURCE_TEXT = "BBBBBBBBBB";

    private static final String DEFAULT_ARTICLE_ASSOCIATION = "AAAAAAAAAA";
    private static final String UPDATED_ARTICLE_ASSOCIATION = "BBBBBBBBBB";

    private static final String DEFAULT_BUSSINESS_REG_NO = "AAAAAAAAAA";
    private static final String UPDATED_BUSSINESS_REG_NO = "BBBBBBBBBB";

    private static final Double DEFAULT_FINANCIAL_ASSET_1 = 1D;
    private static final Double UPDATED_FINANCIAL_ASSET_1 = 2D;

    private static final Double DEFAULT_FINANCIAL_ASSET_2 = 1D;
    private static final Double UPDATED_FINANCIAL_ASSET_2 = 2D;

    private static final Double DEFAULT_FINANCIAL_ASSET_3 = 1D;
    private static final Double UPDATED_FINANCIAL_ASSET_3 = 2D;

    private static final Double DEFAULT_OPERATING_PROFIT_1 = 1D;
    private static final Double UPDATED_OPERATING_PROFIT_1 = 2D;

    private static final Double DEFAULT_OPERATING_PROFIT_2 = 1D;
    private static final Double UPDATED_OPERATING_PROFIT_2 = 2D;

    private static final Double DEFAULT_OPERATING_PROFIT_3 = 1D;
    private static final Double UPDATED_OPERATING_PROFIT_3 = 2D;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_INVESTMENT_OBJECTIVE = "AAAAAAAAAA";
    private static final String UPDATED_INVESTMENT_OBJECTIVE = "BBBBBBBBBB";

    private static final String DEFAULT_DIRECT_SID = "AAAAAAAAAA";
    private static final String UPDATED_DIRECT_SID = "BBBBBBBBBB";

    private static final String DEFAULT_ASSET_OWNER = "A";
    private static final String UPDATED_ASSET_OWNER = "B";

    private static final String DEFAULT_SUP_DOC_TYPE = "A";
    private static final String UPDATED_SUP_DOC_TYPE = "B";

    private static final LocalDate DEFAULT_SUP_DOC_EXP_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SUP_DOC_EXP_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_TAX_ID = 1L;
    private static final Long UPDATED_TAX_ID = 2L;

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
    private AccountInstitutionRegisterRepository accountInstitutionRegisterRepository;

    @Autowired
    private AccountInstitutionRegisterMapper accountInstitutionRegisterMapper;

    @Autowired
    private AccountInstitutionRegisterService accountInstitutionRegisterService;

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

    private MockMvc restAccountInstitutionRegisterMockMvc;

    private AccountInstitutionRegister accountInstitutionRegister;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AccountInstitutionRegisterResource accountInstitutionRegisterResource = new AccountInstitutionRegisterResource(accountInstitutionRegisterService);
        this.restAccountInstitutionRegisterMockMvc = MockMvcBuilders.standaloneSetup(accountInstitutionRegisterResource)
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
    public static AccountInstitutionRegister createEntity(EntityManager em) {
        AccountInstitutionRegister accountInstitutionRegister = new AccountInstitutionRegister()
            .sid(DEFAULT_SID)
            .companyName(DEFAULT_COMPANY_NAME)
            .bicCode(DEFAULT_BIC_CODE)
            .legalDomicileId(DEFAULT_LEGAL_DOMICILE_ID)
            .npwp(DEFAULT_NPWP)
            .npwpRegDate(DEFAULT_NPWP_REG_DATE)
            .skd(DEFAULT_SKD)
            .skdExpDate(DEFAULT_SKD_EXP_DATE)
            .companyEstablishPlce(DEFAULT_COMPANY_ESTABLISH_PLCE)
            .companyEstablishDate(DEFAULT_COMPANY_ESTABLISH_DATE)
            .businessType(DEFAULT_BUSINESS_TYPE)
            .companyChracteristic(DEFAULT_COMPANY_CHRACTERISTIC)
            .fundSource(DEFAULT_FUND_SOURCE)
            .fundSourceText(DEFAULT_FUND_SOURCE_TEXT)
            .articleAssociation(DEFAULT_ARTICLE_ASSOCIATION)
            .bussinessRegNo(DEFAULT_BUSSINESS_REG_NO)
            .financialAsset1(DEFAULT_FINANCIAL_ASSET_1)
            .financialAsset2(DEFAULT_FINANCIAL_ASSET_2)
            .financialAsset3(DEFAULT_FINANCIAL_ASSET_3)
            .operatingProfit1(DEFAULT_OPERATING_PROFIT_1)
            .operatingProfit2(DEFAULT_OPERATING_PROFIT_2)
            .operatingProfit3(DEFAULT_OPERATING_PROFIT_3)
            .description(DEFAULT_DESCRIPTION)
            .investmentObjective(DEFAULT_INVESTMENT_OBJECTIVE)
            .directSid(DEFAULT_DIRECT_SID)
            .assetOwner(DEFAULT_ASSET_OWNER)
            .supDocType(DEFAULT_SUP_DOC_TYPE)
            .supDocExpDate(DEFAULT_SUP_DOC_EXP_DATE)
            .taxId(DEFAULT_TAX_ID)
            .createSystemDate(DEFAULT_CREATE_SYSTEM_DATE)
            .createDate(DEFAULT_CREATE_DATE)
            .createUserId(DEFAULT_CREATE_USER_ID)
            .lastModificationSystemDate(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(DEFAULT_LAST_MODIFICATION_DATE)
            .lastModificationUserId(DEFAULT_LAST_MODIFICATION_USER_ID);
        return accountInstitutionRegister;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AccountInstitutionRegister createUpdatedEntity(EntityManager em) {
        AccountInstitutionRegister accountInstitutionRegister = new AccountInstitutionRegister()
            .sid(UPDATED_SID)
            .companyName(UPDATED_COMPANY_NAME)
            .bicCode(UPDATED_BIC_CODE)
            .legalDomicileId(UPDATED_LEGAL_DOMICILE_ID)
            .npwp(UPDATED_NPWP)
            .npwpRegDate(UPDATED_NPWP_REG_DATE)
            .skd(UPDATED_SKD)
            .skdExpDate(UPDATED_SKD_EXP_DATE)
            .companyEstablishPlce(UPDATED_COMPANY_ESTABLISH_PLCE)
            .companyEstablishDate(UPDATED_COMPANY_ESTABLISH_DATE)
            .businessType(UPDATED_BUSINESS_TYPE)
            .companyChracteristic(UPDATED_COMPANY_CHRACTERISTIC)
            .fundSource(UPDATED_FUND_SOURCE)
            .fundSourceText(UPDATED_FUND_SOURCE_TEXT)
            .articleAssociation(UPDATED_ARTICLE_ASSOCIATION)
            .bussinessRegNo(UPDATED_BUSSINESS_REG_NO)
            .financialAsset1(UPDATED_FINANCIAL_ASSET_1)
            .financialAsset2(UPDATED_FINANCIAL_ASSET_2)
            .financialAsset3(UPDATED_FINANCIAL_ASSET_3)
            .operatingProfit1(UPDATED_OPERATING_PROFIT_1)
            .operatingProfit2(UPDATED_OPERATING_PROFIT_2)
            .operatingProfit3(UPDATED_OPERATING_PROFIT_3)
            .description(UPDATED_DESCRIPTION)
            .investmentObjective(UPDATED_INVESTMENT_OBJECTIVE)
            .directSid(UPDATED_DIRECT_SID)
            .assetOwner(UPDATED_ASSET_OWNER)
            .supDocType(UPDATED_SUP_DOC_TYPE)
            .supDocExpDate(UPDATED_SUP_DOC_EXP_DATE)
            .taxId(UPDATED_TAX_ID)
            .createSystemDate(UPDATED_CREATE_SYSTEM_DATE)
            .createDate(UPDATED_CREATE_DATE)
            .createUserId(UPDATED_CREATE_USER_ID)
            .lastModificationSystemDate(UPDATED_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(UPDATED_LAST_MODIFICATION_DATE)
            .lastModificationUserId(UPDATED_LAST_MODIFICATION_USER_ID);
        return accountInstitutionRegister;
    }

    @BeforeEach
    public void initTest() {
        accountInstitutionRegister = createEntity(em);
    }

    @Test
    @Transactional
    public void createAccountInstitutionRegister() throws Exception {
        int databaseSizeBeforeCreate = accountInstitutionRegisterRepository.findAll().size();

        // Create the AccountInstitutionRegister
        AccountInstitutionRegisterDTO accountInstitutionRegisterDTO = accountInstitutionRegisterMapper.toDto(accountInstitutionRegister);
        restAccountInstitutionRegisterMockMvc.perform(post("/api/account-institution-registers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountInstitutionRegisterDTO)))
            .andExpect(status().isCreated());

        // Validate the AccountInstitutionRegister in the database
        List<AccountInstitutionRegister> accountInstitutionRegisterList = accountInstitutionRegisterRepository.findAll();
        assertThat(accountInstitutionRegisterList).hasSize(databaseSizeBeforeCreate + 1);
        AccountInstitutionRegister testAccountInstitutionRegister = accountInstitutionRegisterList.get(accountInstitutionRegisterList.size() - 1);
        assertThat(testAccountInstitutionRegister.getSid()).isEqualTo(DEFAULT_SID);
        assertThat(testAccountInstitutionRegister.getCompanyName()).isEqualTo(DEFAULT_COMPANY_NAME);
        assertThat(testAccountInstitutionRegister.getBicCode()).isEqualTo(DEFAULT_BIC_CODE);
        assertThat(testAccountInstitutionRegister.getLegalDomicileId()).isEqualTo(DEFAULT_LEGAL_DOMICILE_ID);
        assertThat(testAccountInstitutionRegister.getNpwp()).isEqualTo(DEFAULT_NPWP);
        assertThat(testAccountInstitutionRegister.getNpwpRegDate()).isEqualTo(DEFAULT_NPWP_REG_DATE);
        assertThat(testAccountInstitutionRegister.getSkd()).isEqualTo(DEFAULT_SKD);
        assertThat(testAccountInstitutionRegister.getSkdExpDate()).isEqualTo(DEFAULT_SKD_EXP_DATE);
        assertThat(testAccountInstitutionRegister.getCompanyEstablishPlce()).isEqualTo(DEFAULT_COMPANY_ESTABLISH_PLCE);
        assertThat(testAccountInstitutionRegister.getCompanyEstablishDate()).isEqualTo(DEFAULT_COMPANY_ESTABLISH_DATE);
        assertThat(testAccountInstitutionRegister.getBusinessType()).isEqualTo(DEFAULT_BUSINESS_TYPE);
        assertThat(testAccountInstitutionRegister.getCompanyChracteristic()).isEqualTo(DEFAULT_COMPANY_CHRACTERISTIC);
        assertThat(testAccountInstitutionRegister.getFundSource()).isEqualTo(DEFAULT_FUND_SOURCE);
        assertThat(testAccountInstitutionRegister.getFundSourceText()).isEqualTo(DEFAULT_FUND_SOURCE_TEXT);
        assertThat(testAccountInstitutionRegister.getArticleAssociation()).isEqualTo(DEFAULT_ARTICLE_ASSOCIATION);
        assertThat(testAccountInstitutionRegister.getBussinessRegNo()).isEqualTo(DEFAULT_BUSSINESS_REG_NO);
        assertThat(testAccountInstitutionRegister.getFinancialAsset1()).isEqualTo(DEFAULT_FINANCIAL_ASSET_1);
        assertThat(testAccountInstitutionRegister.getFinancialAsset2()).isEqualTo(DEFAULT_FINANCIAL_ASSET_2);
        assertThat(testAccountInstitutionRegister.getFinancialAsset3()).isEqualTo(DEFAULT_FINANCIAL_ASSET_3);
        assertThat(testAccountInstitutionRegister.getOperatingProfit1()).isEqualTo(DEFAULT_OPERATING_PROFIT_1);
        assertThat(testAccountInstitutionRegister.getOperatingProfit2()).isEqualTo(DEFAULT_OPERATING_PROFIT_2);
        assertThat(testAccountInstitutionRegister.getOperatingProfit3()).isEqualTo(DEFAULT_OPERATING_PROFIT_3);
        assertThat(testAccountInstitutionRegister.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testAccountInstitutionRegister.getInvestmentObjective()).isEqualTo(DEFAULT_INVESTMENT_OBJECTIVE);
        assertThat(testAccountInstitutionRegister.getDirectSid()).isEqualTo(DEFAULT_DIRECT_SID);
        assertThat(testAccountInstitutionRegister.getAssetOwner()).isEqualTo(DEFAULT_ASSET_OWNER);
        assertThat(testAccountInstitutionRegister.getSupDocType()).isEqualTo(DEFAULT_SUP_DOC_TYPE);
        assertThat(testAccountInstitutionRegister.getSupDocExpDate()).isEqualTo(DEFAULT_SUP_DOC_EXP_DATE);
        assertThat(testAccountInstitutionRegister.getTaxId()).isEqualTo(DEFAULT_TAX_ID);
        assertThat(testAccountInstitutionRegister.getCreateSystemDate()).isEqualTo(DEFAULT_CREATE_SYSTEM_DATE);
        assertThat(testAccountInstitutionRegister.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testAccountInstitutionRegister.getCreateUserId()).isEqualTo(DEFAULT_CREATE_USER_ID);
        assertThat(testAccountInstitutionRegister.getLastModificationSystemDate()).isEqualTo(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE);
        assertThat(testAccountInstitutionRegister.getLastModificationDate()).isEqualTo(DEFAULT_LAST_MODIFICATION_DATE);
        assertThat(testAccountInstitutionRegister.getLastModificationUserId()).isEqualTo(DEFAULT_LAST_MODIFICATION_USER_ID);
    }

    @Test
    @Transactional
    public void createAccountInstitutionRegisterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = accountInstitutionRegisterRepository.findAll().size();

        // Create the AccountInstitutionRegister with an existing ID
        accountInstitutionRegister.setId(1L);
        AccountInstitutionRegisterDTO accountInstitutionRegisterDTO = accountInstitutionRegisterMapper.toDto(accountInstitutionRegister);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAccountInstitutionRegisterMockMvc.perform(post("/api/account-institution-registers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountInstitutionRegisterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AccountInstitutionRegister in the database
        List<AccountInstitutionRegister> accountInstitutionRegisterList = accountInstitutionRegisterRepository.findAll();
        assertThat(accountInstitutionRegisterList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAccountInstitutionRegisters() throws Exception {
        // Initialize the database
        accountInstitutionRegisterRepository.saveAndFlush(accountInstitutionRegister);

        // Get all the accountInstitutionRegisterList
        restAccountInstitutionRegisterMockMvc.perform(get("/api/account-institution-registers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(accountInstitutionRegister.getId().intValue())))
            .andExpect(jsonPath("$.[*].sid").value(hasItem(DEFAULT_SID)))
            .andExpect(jsonPath("$.[*].companyName").value(hasItem(DEFAULT_COMPANY_NAME)))
            .andExpect(jsonPath("$.[*].bicCode").value(hasItem(DEFAULT_BIC_CODE)))
            .andExpect(jsonPath("$.[*].legalDomicileId").value(hasItem(DEFAULT_LEGAL_DOMICILE_ID.intValue())))
            .andExpect(jsonPath("$.[*].npwp").value(hasItem(DEFAULT_NPWP)))
            .andExpect(jsonPath("$.[*].npwpRegDate").value(hasItem(DEFAULT_NPWP_REG_DATE.toString())))
            .andExpect(jsonPath("$.[*].skd").value(hasItem(DEFAULT_SKD)))
            .andExpect(jsonPath("$.[*].skdExpDate").value(hasItem(DEFAULT_SKD_EXP_DATE.toString())))
            .andExpect(jsonPath("$.[*].companyEstablishPlce").value(hasItem(DEFAULT_COMPANY_ESTABLISH_PLCE)))
            .andExpect(jsonPath("$.[*].companyEstablishDate").value(hasItem(DEFAULT_COMPANY_ESTABLISH_DATE.toString())))
            .andExpect(jsonPath("$.[*].businessType").value(hasItem(DEFAULT_BUSINESS_TYPE)))
            .andExpect(jsonPath("$.[*].companyChracteristic").value(hasItem(DEFAULT_COMPANY_CHRACTERISTIC)))
            .andExpect(jsonPath("$.[*].fundSource").value(hasItem(DEFAULT_FUND_SOURCE)))
            .andExpect(jsonPath("$.[*].fundSourceText").value(hasItem(DEFAULT_FUND_SOURCE_TEXT)))
            .andExpect(jsonPath("$.[*].articleAssociation").value(hasItem(DEFAULT_ARTICLE_ASSOCIATION)))
            .andExpect(jsonPath("$.[*].bussinessRegNo").value(hasItem(DEFAULT_BUSSINESS_REG_NO)))
            .andExpect(jsonPath("$.[*].financialAsset1").value(hasItem(DEFAULT_FINANCIAL_ASSET_1.doubleValue())))
            .andExpect(jsonPath("$.[*].financialAsset2").value(hasItem(DEFAULT_FINANCIAL_ASSET_2.doubleValue())))
            .andExpect(jsonPath("$.[*].financialAsset3").value(hasItem(DEFAULT_FINANCIAL_ASSET_3.doubleValue())))
            .andExpect(jsonPath("$.[*].operatingProfit1").value(hasItem(DEFAULT_OPERATING_PROFIT_1.doubleValue())))
            .andExpect(jsonPath("$.[*].operatingProfit2").value(hasItem(DEFAULT_OPERATING_PROFIT_2.doubleValue())))
            .andExpect(jsonPath("$.[*].operatingProfit3").value(hasItem(DEFAULT_OPERATING_PROFIT_3.doubleValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].investmentObjective").value(hasItem(DEFAULT_INVESTMENT_OBJECTIVE)))
            .andExpect(jsonPath("$.[*].directSid").value(hasItem(DEFAULT_DIRECT_SID)))
            .andExpect(jsonPath("$.[*].assetOwner").value(hasItem(DEFAULT_ASSET_OWNER)))
            .andExpect(jsonPath("$.[*].supDocType").value(hasItem(DEFAULT_SUP_DOC_TYPE)))
            .andExpect(jsonPath("$.[*].supDocExpDate").value(hasItem(DEFAULT_SUP_DOC_EXP_DATE.toString())))
            .andExpect(jsonPath("$.[*].taxId").value(hasItem(DEFAULT_TAX_ID.intValue())))
            .andExpect(jsonPath("$.[*].createSystemDate").value(hasItem(DEFAULT_CREATE_SYSTEM_DATE.toString())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(sameInstant(DEFAULT_CREATE_DATE))))
            .andExpect(jsonPath("$.[*].createUserId").value(hasItem(DEFAULT_CREATE_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].lastModificationSystemDate").value(hasItem(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModificationDate").value(hasItem(sameInstant(DEFAULT_LAST_MODIFICATION_DATE))))
            .andExpect(jsonPath("$.[*].lastModificationUserId").value(hasItem(DEFAULT_LAST_MODIFICATION_USER_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getAccountInstitutionRegister() throws Exception {
        // Initialize the database
        accountInstitutionRegisterRepository.saveAndFlush(accountInstitutionRegister);

        // Get the accountInstitutionRegister
        restAccountInstitutionRegisterMockMvc.perform(get("/api/account-institution-registers/{id}", accountInstitutionRegister.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(accountInstitutionRegister.getId().intValue()))
            .andExpect(jsonPath("$.sid").value(DEFAULT_SID))
            .andExpect(jsonPath("$.companyName").value(DEFAULT_COMPANY_NAME))
            .andExpect(jsonPath("$.bicCode").value(DEFAULT_BIC_CODE))
            .andExpect(jsonPath("$.legalDomicileId").value(DEFAULT_LEGAL_DOMICILE_ID.intValue()))
            .andExpect(jsonPath("$.npwp").value(DEFAULT_NPWP))
            .andExpect(jsonPath("$.npwpRegDate").value(DEFAULT_NPWP_REG_DATE.toString()))
            .andExpect(jsonPath("$.skd").value(DEFAULT_SKD))
            .andExpect(jsonPath("$.skdExpDate").value(DEFAULT_SKD_EXP_DATE.toString()))
            .andExpect(jsonPath("$.companyEstablishPlce").value(DEFAULT_COMPANY_ESTABLISH_PLCE))
            .andExpect(jsonPath("$.companyEstablishDate").value(DEFAULT_COMPANY_ESTABLISH_DATE.toString()))
            .andExpect(jsonPath("$.businessType").value(DEFAULT_BUSINESS_TYPE))
            .andExpect(jsonPath("$.companyChracteristic").value(DEFAULT_COMPANY_CHRACTERISTIC))
            .andExpect(jsonPath("$.fundSource").value(DEFAULT_FUND_SOURCE))
            .andExpect(jsonPath("$.fundSourceText").value(DEFAULT_FUND_SOURCE_TEXT))
            .andExpect(jsonPath("$.articleAssociation").value(DEFAULT_ARTICLE_ASSOCIATION))
            .andExpect(jsonPath("$.bussinessRegNo").value(DEFAULT_BUSSINESS_REG_NO))
            .andExpect(jsonPath("$.financialAsset1").value(DEFAULT_FINANCIAL_ASSET_1.doubleValue()))
            .andExpect(jsonPath("$.financialAsset2").value(DEFAULT_FINANCIAL_ASSET_2.doubleValue()))
            .andExpect(jsonPath("$.financialAsset3").value(DEFAULT_FINANCIAL_ASSET_3.doubleValue()))
            .andExpect(jsonPath("$.operatingProfit1").value(DEFAULT_OPERATING_PROFIT_1.doubleValue()))
            .andExpect(jsonPath("$.operatingProfit2").value(DEFAULT_OPERATING_PROFIT_2.doubleValue()))
            .andExpect(jsonPath("$.operatingProfit3").value(DEFAULT_OPERATING_PROFIT_3.doubleValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.investmentObjective").value(DEFAULT_INVESTMENT_OBJECTIVE))
            .andExpect(jsonPath("$.directSid").value(DEFAULT_DIRECT_SID))
            .andExpect(jsonPath("$.assetOwner").value(DEFAULT_ASSET_OWNER))
            .andExpect(jsonPath("$.supDocType").value(DEFAULT_SUP_DOC_TYPE))
            .andExpect(jsonPath("$.supDocExpDate").value(DEFAULT_SUP_DOC_EXP_DATE.toString()))
            .andExpect(jsonPath("$.taxId").value(DEFAULT_TAX_ID.intValue()))
            .andExpect(jsonPath("$.createSystemDate").value(DEFAULT_CREATE_SYSTEM_DATE.toString()))
            .andExpect(jsonPath("$.createDate").value(sameInstant(DEFAULT_CREATE_DATE)))
            .andExpect(jsonPath("$.createUserId").value(DEFAULT_CREATE_USER_ID.intValue()))
            .andExpect(jsonPath("$.lastModificationSystemDate").value(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE.toString()))
            .andExpect(jsonPath("$.lastModificationDate").value(sameInstant(DEFAULT_LAST_MODIFICATION_DATE)))
            .andExpect(jsonPath("$.lastModificationUserId").value(DEFAULT_LAST_MODIFICATION_USER_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAccountInstitutionRegister() throws Exception {
        // Get the accountInstitutionRegister
        restAccountInstitutionRegisterMockMvc.perform(get("/api/account-institution-registers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAccountInstitutionRegister() throws Exception {
        // Initialize the database
        accountInstitutionRegisterRepository.saveAndFlush(accountInstitutionRegister);

        int databaseSizeBeforeUpdate = accountInstitutionRegisterRepository.findAll().size();

        // Update the accountInstitutionRegister
        AccountInstitutionRegister updatedAccountInstitutionRegister = accountInstitutionRegisterRepository.findById(accountInstitutionRegister.getId()).get();
        // Disconnect from session so that the updates on updatedAccountInstitutionRegister are not directly saved in db
        em.detach(updatedAccountInstitutionRegister);
        updatedAccountInstitutionRegister
            .sid(UPDATED_SID)
            .companyName(UPDATED_COMPANY_NAME)
            .bicCode(UPDATED_BIC_CODE)
            .legalDomicileId(UPDATED_LEGAL_DOMICILE_ID)
            .npwp(UPDATED_NPWP)
            .npwpRegDate(UPDATED_NPWP_REG_DATE)
            .skd(UPDATED_SKD)
            .skdExpDate(UPDATED_SKD_EXP_DATE)
            .companyEstablishPlce(UPDATED_COMPANY_ESTABLISH_PLCE)
            .companyEstablishDate(UPDATED_COMPANY_ESTABLISH_DATE)
            .businessType(UPDATED_BUSINESS_TYPE)
            .companyChracteristic(UPDATED_COMPANY_CHRACTERISTIC)
            .fundSource(UPDATED_FUND_SOURCE)
            .fundSourceText(UPDATED_FUND_SOURCE_TEXT)
            .articleAssociation(UPDATED_ARTICLE_ASSOCIATION)
            .bussinessRegNo(UPDATED_BUSSINESS_REG_NO)
            .financialAsset1(UPDATED_FINANCIAL_ASSET_1)
            .financialAsset2(UPDATED_FINANCIAL_ASSET_2)
            .financialAsset3(UPDATED_FINANCIAL_ASSET_3)
            .operatingProfit1(UPDATED_OPERATING_PROFIT_1)
            .operatingProfit2(UPDATED_OPERATING_PROFIT_2)
            .operatingProfit3(UPDATED_OPERATING_PROFIT_3)
            .description(UPDATED_DESCRIPTION)
            .investmentObjective(UPDATED_INVESTMENT_OBJECTIVE)
            .directSid(UPDATED_DIRECT_SID)
            .assetOwner(UPDATED_ASSET_OWNER)
            .supDocType(UPDATED_SUP_DOC_TYPE)
            .supDocExpDate(UPDATED_SUP_DOC_EXP_DATE)
            .taxId(UPDATED_TAX_ID)
            .createSystemDate(UPDATED_CREATE_SYSTEM_DATE)
            .createDate(UPDATED_CREATE_DATE)
            .createUserId(UPDATED_CREATE_USER_ID)
            .lastModificationSystemDate(UPDATED_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(UPDATED_LAST_MODIFICATION_DATE)
            .lastModificationUserId(UPDATED_LAST_MODIFICATION_USER_ID);
        AccountInstitutionRegisterDTO accountInstitutionRegisterDTO = accountInstitutionRegisterMapper.toDto(updatedAccountInstitutionRegister);

        restAccountInstitutionRegisterMockMvc.perform(put("/api/account-institution-registers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountInstitutionRegisterDTO)))
            .andExpect(status().isOk());

        // Validate the AccountInstitutionRegister in the database
        List<AccountInstitutionRegister> accountInstitutionRegisterList = accountInstitutionRegisterRepository.findAll();
        assertThat(accountInstitutionRegisterList).hasSize(databaseSizeBeforeUpdate);
        AccountInstitutionRegister testAccountInstitutionRegister = accountInstitutionRegisterList.get(accountInstitutionRegisterList.size() - 1);
        assertThat(testAccountInstitutionRegister.getSid()).isEqualTo(UPDATED_SID);
        assertThat(testAccountInstitutionRegister.getCompanyName()).isEqualTo(UPDATED_COMPANY_NAME);
        assertThat(testAccountInstitutionRegister.getBicCode()).isEqualTo(UPDATED_BIC_CODE);
        assertThat(testAccountInstitutionRegister.getLegalDomicileId()).isEqualTo(UPDATED_LEGAL_DOMICILE_ID);
        assertThat(testAccountInstitutionRegister.getNpwp()).isEqualTo(UPDATED_NPWP);
        assertThat(testAccountInstitutionRegister.getNpwpRegDate()).isEqualTo(UPDATED_NPWP_REG_DATE);
        assertThat(testAccountInstitutionRegister.getSkd()).isEqualTo(UPDATED_SKD);
        assertThat(testAccountInstitutionRegister.getSkdExpDate()).isEqualTo(UPDATED_SKD_EXP_DATE);
        assertThat(testAccountInstitutionRegister.getCompanyEstablishPlce()).isEqualTo(UPDATED_COMPANY_ESTABLISH_PLCE);
        assertThat(testAccountInstitutionRegister.getCompanyEstablishDate()).isEqualTo(UPDATED_COMPANY_ESTABLISH_DATE);
        assertThat(testAccountInstitutionRegister.getBusinessType()).isEqualTo(UPDATED_BUSINESS_TYPE);
        assertThat(testAccountInstitutionRegister.getCompanyChracteristic()).isEqualTo(UPDATED_COMPANY_CHRACTERISTIC);
        assertThat(testAccountInstitutionRegister.getFundSource()).isEqualTo(UPDATED_FUND_SOURCE);
        assertThat(testAccountInstitutionRegister.getFundSourceText()).isEqualTo(UPDATED_FUND_SOURCE_TEXT);
        assertThat(testAccountInstitutionRegister.getArticleAssociation()).isEqualTo(UPDATED_ARTICLE_ASSOCIATION);
        assertThat(testAccountInstitutionRegister.getBussinessRegNo()).isEqualTo(UPDATED_BUSSINESS_REG_NO);
        assertThat(testAccountInstitutionRegister.getFinancialAsset1()).isEqualTo(UPDATED_FINANCIAL_ASSET_1);
        assertThat(testAccountInstitutionRegister.getFinancialAsset2()).isEqualTo(UPDATED_FINANCIAL_ASSET_2);
        assertThat(testAccountInstitutionRegister.getFinancialAsset3()).isEqualTo(UPDATED_FINANCIAL_ASSET_3);
        assertThat(testAccountInstitutionRegister.getOperatingProfit1()).isEqualTo(UPDATED_OPERATING_PROFIT_1);
        assertThat(testAccountInstitutionRegister.getOperatingProfit2()).isEqualTo(UPDATED_OPERATING_PROFIT_2);
        assertThat(testAccountInstitutionRegister.getOperatingProfit3()).isEqualTo(UPDATED_OPERATING_PROFIT_3);
        assertThat(testAccountInstitutionRegister.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testAccountInstitutionRegister.getInvestmentObjective()).isEqualTo(UPDATED_INVESTMENT_OBJECTIVE);
        assertThat(testAccountInstitutionRegister.getDirectSid()).isEqualTo(UPDATED_DIRECT_SID);
        assertThat(testAccountInstitutionRegister.getAssetOwner()).isEqualTo(UPDATED_ASSET_OWNER);
        assertThat(testAccountInstitutionRegister.getSupDocType()).isEqualTo(UPDATED_SUP_DOC_TYPE);
        assertThat(testAccountInstitutionRegister.getSupDocExpDate()).isEqualTo(UPDATED_SUP_DOC_EXP_DATE);
        assertThat(testAccountInstitutionRegister.getTaxId()).isEqualTo(UPDATED_TAX_ID);
        assertThat(testAccountInstitutionRegister.getCreateSystemDate()).isEqualTo(UPDATED_CREATE_SYSTEM_DATE);
        assertThat(testAccountInstitutionRegister.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testAccountInstitutionRegister.getCreateUserId()).isEqualTo(UPDATED_CREATE_USER_ID);
        assertThat(testAccountInstitutionRegister.getLastModificationSystemDate()).isEqualTo(UPDATED_LAST_MODIFICATION_SYSTEM_DATE);
        assertThat(testAccountInstitutionRegister.getLastModificationDate()).isEqualTo(UPDATED_LAST_MODIFICATION_DATE);
        assertThat(testAccountInstitutionRegister.getLastModificationUserId()).isEqualTo(UPDATED_LAST_MODIFICATION_USER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingAccountInstitutionRegister() throws Exception {
        int databaseSizeBeforeUpdate = accountInstitutionRegisterRepository.findAll().size();

        // Create the AccountInstitutionRegister
        AccountInstitutionRegisterDTO accountInstitutionRegisterDTO = accountInstitutionRegisterMapper.toDto(accountInstitutionRegister);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAccountInstitutionRegisterMockMvc.perform(put("/api/account-institution-registers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accountInstitutionRegisterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AccountInstitutionRegister in the database
        List<AccountInstitutionRegister> accountInstitutionRegisterList = accountInstitutionRegisterRepository.findAll();
        assertThat(accountInstitutionRegisterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAccountInstitutionRegister() throws Exception {
        // Initialize the database
        accountInstitutionRegisterRepository.saveAndFlush(accountInstitutionRegister);

        int databaseSizeBeforeDelete = accountInstitutionRegisterRepository.findAll().size();

        // Delete the accountInstitutionRegister
        restAccountInstitutionRegisterMockMvc.perform(delete("/api/account-institution-registers/{id}", accountInstitutionRegister.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AccountInstitutionRegister> accountInstitutionRegisterList = accountInstitutionRegisterRepository.findAll();
        assertThat(accountInstitutionRegisterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
