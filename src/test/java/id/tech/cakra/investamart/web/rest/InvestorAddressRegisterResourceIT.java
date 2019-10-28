package id.tech.cakra.investamart.web.rest;

import id.tech.cakra.investamart.InvestaregistersvcApp;
import id.tech.cakra.investamart.domain.InvestorAddressRegister;
import id.tech.cakra.investamart.repository.InvestorAddressRegisterRepository;
import id.tech.cakra.investamart.service.InvestorAddressRegisterService;
import id.tech.cakra.investamart.service.dto.InvestorAddressRegisterDTO;
import id.tech.cakra.investamart.service.mapper.InvestorAddressRegisterMapper;
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
 * Integration tests for the {@link InvestorAddressRegisterResource} REST controller.
 */
@SpringBootTest(classes = InvestaregistersvcApp.class)
public class InvestorAddressRegisterResourceIT {

    private static final String DEFAULT_ADDRESS_TYPE = "A";
    private static final String UPDATED_ADDRESS_TYPE = "B";

    private static final String DEFAULT_ADDRESS_1 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_2 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_2 = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_3 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_3 = "BBBBBBBBBB";

    private static final String DEFAULT_POSTAL_CODE = "AAAAA";
    private static final String UPDATED_POSTAL_CODE = "BBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    private static final Long DEFAULT_COUNTRY_ID = 1L;
    private static final Long UPDATED_COUNTRY_ID = 2L;

    private static final Long DEFAULT_PROVINCE_ID = 1L;
    private static final Long UPDATED_PROVINCE_ID = 2L;

    private static final Long DEFAULT_CITY_ID = 1L;
    private static final Long UPDATED_CITY_ID = 2L;

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
    private InvestorAddressRegisterRepository investorAddressRegisterRepository;

    @Autowired
    private InvestorAddressRegisterMapper investorAddressRegisterMapper;

    @Autowired
    private InvestorAddressRegisterService investorAddressRegisterService;

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

    private MockMvc restInvestorAddressRegisterMockMvc;

    private InvestorAddressRegister investorAddressRegister;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final InvestorAddressRegisterResource investorAddressRegisterResource = new InvestorAddressRegisterResource(investorAddressRegisterService);
        this.restInvestorAddressRegisterMockMvc = MockMvcBuilders.standaloneSetup(investorAddressRegisterResource)
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
    public static InvestorAddressRegister createEntity(EntityManager em) {
        InvestorAddressRegister investorAddressRegister = new InvestorAddressRegister()
            .addressType(DEFAULT_ADDRESS_TYPE)
            .address1(DEFAULT_ADDRESS_1)
            .address2(DEFAULT_ADDRESS_2)
            .address3(DEFAULT_ADDRESS_3)
            .postalCode(DEFAULT_POSTAL_CODE)
            .phone(DEFAULT_PHONE)
            .mobilePhone(DEFAULT_MOBILE_PHONE)
            .email(DEFAULT_EMAIL)
            .fax(DEFAULT_FAX)
            .countryId(DEFAULT_COUNTRY_ID)
            .provinceId(DEFAULT_PROVINCE_ID)
            .cityId(DEFAULT_CITY_ID)
            .createSystemDate(DEFAULT_CREATE_SYSTEM_DATE)
            .createDate(DEFAULT_CREATE_DATE)
            .createUserId(DEFAULT_CREATE_USER_ID)
            .lastModificationSystemDate(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(DEFAULT_LAST_MODIFICATION_DATE)
            .lastModificationUserId(DEFAULT_LAST_MODIFICATION_USER_ID);
        return investorAddressRegister;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InvestorAddressRegister createUpdatedEntity(EntityManager em) {
        InvestorAddressRegister investorAddressRegister = new InvestorAddressRegister()
            .addressType(UPDATED_ADDRESS_TYPE)
            .address1(UPDATED_ADDRESS_1)
            .address2(UPDATED_ADDRESS_2)
            .address3(UPDATED_ADDRESS_3)
            .postalCode(UPDATED_POSTAL_CODE)
            .phone(UPDATED_PHONE)
            .mobilePhone(UPDATED_MOBILE_PHONE)
            .email(UPDATED_EMAIL)
            .fax(UPDATED_FAX)
            .countryId(UPDATED_COUNTRY_ID)
            .provinceId(UPDATED_PROVINCE_ID)
            .cityId(UPDATED_CITY_ID)
            .createSystemDate(UPDATED_CREATE_SYSTEM_DATE)
            .createDate(UPDATED_CREATE_DATE)
            .createUserId(UPDATED_CREATE_USER_ID)
            .lastModificationSystemDate(UPDATED_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(UPDATED_LAST_MODIFICATION_DATE)
            .lastModificationUserId(UPDATED_LAST_MODIFICATION_USER_ID);
        return investorAddressRegister;
    }

    @BeforeEach
    public void initTest() {
        investorAddressRegister = createEntity(em);
    }

    @Test
    @Transactional
    public void createInvestorAddressRegister() throws Exception {
        int databaseSizeBeforeCreate = investorAddressRegisterRepository.findAll().size();

        // Create the InvestorAddressRegister
        InvestorAddressRegisterDTO investorAddressRegisterDTO = investorAddressRegisterMapper.toDto(investorAddressRegister);
        restInvestorAddressRegisterMockMvc.perform(post("/api/investor-address-registers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investorAddressRegisterDTO)))
            .andExpect(status().isCreated());

        // Validate the InvestorAddressRegister in the database
        List<InvestorAddressRegister> investorAddressRegisterList = investorAddressRegisterRepository.findAll();
        assertThat(investorAddressRegisterList).hasSize(databaseSizeBeforeCreate + 1);
        InvestorAddressRegister testInvestorAddressRegister = investorAddressRegisterList.get(investorAddressRegisterList.size() - 1);
        assertThat(testInvestorAddressRegister.getAddressType()).isEqualTo(DEFAULT_ADDRESS_TYPE);
        assertThat(testInvestorAddressRegister.getAddress1()).isEqualTo(DEFAULT_ADDRESS_1);
        assertThat(testInvestorAddressRegister.getAddress2()).isEqualTo(DEFAULT_ADDRESS_2);
        assertThat(testInvestorAddressRegister.getAddress3()).isEqualTo(DEFAULT_ADDRESS_3);
        assertThat(testInvestorAddressRegister.getPostalCode()).isEqualTo(DEFAULT_POSTAL_CODE);
        assertThat(testInvestorAddressRegister.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testInvestorAddressRegister.getMobilePhone()).isEqualTo(DEFAULT_MOBILE_PHONE);
        assertThat(testInvestorAddressRegister.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testInvestorAddressRegister.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testInvestorAddressRegister.getCountryId()).isEqualTo(DEFAULT_COUNTRY_ID);
        assertThat(testInvestorAddressRegister.getProvinceId()).isEqualTo(DEFAULT_PROVINCE_ID);
        assertThat(testInvestorAddressRegister.getCityId()).isEqualTo(DEFAULT_CITY_ID);
        assertThat(testInvestorAddressRegister.getCreateSystemDate()).isEqualTo(DEFAULT_CREATE_SYSTEM_DATE);
        assertThat(testInvestorAddressRegister.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testInvestorAddressRegister.getCreateUserId()).isEqualTo(DEFAULT_CREATE_USER_ID);
        assertThat(testInvestorAddressRegister.getLastModificationSystemDate()).isEqualTo(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE);
        assertThat(testInvestorAddressRegister.getLastModificationDate()).isEqualTo(DEFAULT_LAST_MODIFICATION_DATE);
        assertThat(testInvestorAddressRegister.getLastModificationUserId()).isEqualTo(DEFAULT_LAST_MODIFICATION_USER_ID);
    }

    @Test
    @Transactional
    public void createInvestorAddressRegisterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = investorAddressRegisterRepository.findAll().size();

        // Create the InvestorAddressRegister with an existing ID
        investorAddressRegister.setId(1L);
        InvestorAddressRegisterDTO investorAddressRegisterDTO = investorAddressRegisterMapper.toDto(investorAddressRegister);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInvestorAddressRegisterMockMvc.perform(post("/api/investor-address-registers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investorAddressRegisterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InvestorAddressRegister in the database
        List<InvestorAddressRegister> investorAddressRegisterList = investorAddressRegisterRepository.findAll();
        assertThat(investorAddressRegisterList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllInvestorAddressRegisters() throws Exception {
        // Initialize the database
        investorAddressRegisterRepository.saveAndFlush(investorAddressRegister);

        // Get all the investorAddressRegisterList
        restInvestorAddressRegisterMockMvc.perform(get("/api/investor-address-registers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(investorAddressRegister.getId().intValue())))
            .andExpect(jsonPath("$.[*].addressType").value(hasItem(DEFAULT_ADDRESS_TYPE)))
            .andExpect(jsonPath("$.[*].address1").value(hasItem(DEFAULT_ADDRESS_1)))
            .andExpect(jsonPath("$.[*].address2").value(hasItem(DEFAULT_ADDRESS_2)))
            .andExpect(jsonPath("$.[*].address3").value(hasItem(DEFAULT_ADDRESS_3)))
            .andExpect(jsonPath("$.[*].postalCode").value(hasItem(DEFAULT_POSTAL_CODE)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].mobilePhone").value(hasItem(DEFAULT_MOBILE_PHONE)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX)))
            .andExpect(jsonPath("$.[*].countryId").value(hasItem(DEFAULT_COUNTRY_ID.intValue())))
            .andExpect(jsonPath("$.[*].provinceId").value(hasItem(DEFAULT_PROVINCE_ID.intValue())))
            .andExpect(jsonPath("$.[*].cityId").value(hasItem(DEFAULT_CITY_ID.intValue())))
            .andExpect(jsonPath("$.[*].createSystemDate").value(hasItem(DEFAULT_CREATE_SYSTEM_DATE.toString())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(sameInstant(DEFAULT_CREATE_DATE))))
            .andExpect(jsonPath("$.[*].createUserId").value(hasItem(DEFAULT_CREATE_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].lastModificationSystemDate").value(hasItem(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastModificationDate").value(hasItem(sameInstant(DEFAULT_LAST_MODIFICATION_DATE))))
            .andExpect(jsonPath("$.[*].lastModificationUserId").value(hasItem(DEFAULT_LAST_MODIFICATION_USER_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getInvestorAddressRegister() throws Exception {
        // Initialize the database
        investorAddressRegisterRepository.saveAndFlush(investorAddressRegister);

        // Get the investorAddressRegister
        restInvestorAddressRegisterMockMvc.perform(get("/api/investor-address-registers/{id}", investorAddressRegister.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(investorAddressRegister.getId().intValue()))
            .andExpect(jsonPath("$.addressType").value(DEFAULT_ADDRESS_TYPE))
            .andExpect(jsonPath("$.address1").value(DEFAULT_ADDRESS_1))
            .andExpect(jsonPath("$.address2").value(DEFAULT_ADDRESS_2))
            .andExpect(jsonPath("$.address3").value(DEFAULT_ADDRESS_3))
            .andExpect(jsonPath("$.postalCode").value(DEFAULT_POSTAL_CODE))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.mobilePhone").value(DEFAULT_MOBILE_PHONE))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX))
            .andExpect(jsonPath("$.countryId").value(DEFAULT_COUNTRY_ID.intValue()))
            .andExpect(jsonPath("$.provinceId").value(DEFAULT_PROVINCE_ID.intValue()))
            .andExpect(jsonPath("$.cityId").value(DEFAULT_CITY_ID.intValue()))
            .andExpect(jsonPath("$.createSystemDate").value(DEFAULT_CREATE_SYSTEM_DATE.toString()))
            .andExpect(jsonPath("$.createDate").value(sameInstant(DEFAULT_CREATE_DATE)))
            .andExpect(jsonPath("$.createUserId").value(DEFAULT_CREATE_USER_ID.intValue()))
            .andExpect(jsonPath("$.lastModificationSystemDate").value(DEFAULT_LAST_MODIFICATION_SYSTEM_DATE.toString()))
            .andExpect(jsonPath("$.lastModificationDate").value(sameInstant(DEFAULT_LAST_MODIFICATION_DATE)))
            .andExpect(jsonPath("$.lastModificationUserId").value(DEFAULT_LAST_MODIFICATION_USER_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingInvestorAddressRegister() throws Exception {
        // Get the investorAddressRegister
        restInvestorAddressRegisterMockMvc.perform(get("/api/investor-address-registers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInvestorAddressRegister() throws Exception {
        // Initialize the database
        investorAddressRegisterRepository.saveAndFlush(investorAddressRegister);

        int databaseSizeBeforeUpdate = investorAddressRegisterRepository.findAll().size();

        // Update the investorAddressRegister
        InvestorAddressRegister updatedInvestorAddressRegister = investorAddressRegisterRepository.findById(investorAddressRegister.getId()).get();
        // Disconnect from session so that the updates on updatedInvestorAddressRegister are not directly saved in db
        em.detach(updatedInvestorAddressRegister);
        updatedInvestorAddressRegister
            .addressType(UPDATED_ADDRESS_TYPE)
            .address1(UPDATED_ADDRESS_1)
            .address2(UPDATED_ADDRESS_2)
            .address3(UPDATED_ADDRESS_3)
            .postalCode(UPDATED_POSTAL_CODE)
            .phone(UPDATED_PHONE)
            .mobilePhone(UPDATED_MOBILE_PHONE)
            .email(UPDATED_EMAIL)
            .fax(UPDATED_FAX)
            .countryId(UPDATED_COUNTRY_ID)
            .provinceId(UPDATED_PROVINCE_ID)
            .cityId(UPDATED_CITY_ID)
            .createSystemDate(UPDATED_CREATE_SYSTEM_DATE)
            .createDate(UPDATED_CREATE_DATE)
            .createUserId(UPDATED_CREATE_USER_ID)
            .lastModificationSystemDate(UPDATED_LAST_MODIFICATION_SYSTEM_DATE)
            .lastModificationDate(UPDATED_LAST_MODIFICATION_DATE)
            .lastModificationUserId(UPDATED_LAST_MODIFICATION_USER_ID);
        InvestorAddressRegisterDTO investorAddressRegisterDTO = investorAddressRegisterMapper.toDto(updatedInvestorAddressRegister);

        restInvestorAddressRegisterMockMvc.perform(put("/api/investor-address-registers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investorAddressRegisterDTO)))
            .andExpect(status().isOk());

        // Validate the InvestorAddressRegister in the database
        List<InvestorAddressRegister> investorAddressRegisterList = investorAddressRegisterRepository.findAll();
        assertThat(investorAddressRegisterList).hasSize(databaseSizeBeforeUpdate);
        InvestorAddressRegister testInvestorAddressRegister = investorAddressRegisterList.get(investorAddressRegisterList.size() - 1);
        assertThat(testInvestorAddressRegister.getAddressType()).isEqualTo(UPDATED_ADDRESS_TYPE);
        assertThat(testInvestorAddressRegister.getAddress1()).isEqualTo(UPDATED_ADDRESS_1);
        assertThat(testInvestorAddressRegister.getAddress2()).isEqualTo(UPDATED_ADDRESS_2);
        assertThat(testInvestorAddressRegister.getAddress3()).isEqualTo(UPDATED_ADDRESS_3);
        assertThat(testInvestorAddressRegister.getPostalCode()).isEqualTo(UPDATED_POSTAL_CODE);
        assertThat(testInvestorAddressRegister.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testInvestorAddressRegister.getMobilePhone()).isEqualTo(UPDATED_MOBILE_PHONE);
        assertThat(testInvestorAddressRegister.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testInvestorAddressRegister.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testInvestorAddressRegister.getCountryId()).isEqualTo(UPDATED_COUNTRY_ID);
        assertThat(testInvestorAddressRegister.getProvinceId()).isEqualTo(UPDATED_PROVINCE_ID);
        assertThat(testInvestorAddressRegister.getCityId()).isEqualTo(UPDATED_CITY_ID);
        assertThat(testInvestorAddressRegister.getCreateSystemDate()).isEqualTo(UPDATED_CREATE_SYSTEM_DATE);
        assertThat(testInvestorAddressRegister.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testInvestorAddressRegister.getCreateUserId()).isEqualTo(UPDATED_CREATE_USER_ID);
        assertThat(testInvestorAddressRegister.getLastModificationSystemDate()).isEqualTo(UPDATED_LAST_MODIFICATION_SYSTEM_DATE);
        assertThat(testInvestorAddressRegister.getLastModificationDate()).isEqualTo(UPDATED_LAST_MODIFICATION_DATE);
        assertThat(testInvestorAddressRegister.getLastModificationUserId()).isEqualTo(UPDATED_LAST_MODIFICATION_USER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingInvestorAddressRegister() throws Exception {
        int databaseSizeBeforeUpdate = investorAddressRegisterRepository.findAll().size();

        // Create the InvestorAddressRegister
        InvestorAddressRegisterDTO investorAddressRegisterDTO = investorAddressRegisterMapper.toDto(investorAddressRegister);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInvestorAddressRegisterMockMvc.perform(put("/api/investor-address-registers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investorAddressRegisterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InvestorAddressRegister in the database
        List<InvestorAddressRegister> investorAddressRegisterList = investorAddressRegisterRepository.findAll();
        assertThat(investorAddressRegisterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInvestorAddressRegister() throws Exception {
        // Initialize the database
        investorAddressRegisterRepository.saveAndFlush(investorAddressRegister);

        int databaseSizeBeforeDelete = investorAddressRegisterRepository.findAll().size();

        // Delete the investorAddressRegister
        restInvestorAddressRegisterMockMvc.perform(delete("/api/investor-address-registers/{id}", investorAddressRegister.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InvestorAddressRegister> investorAddressRegisterList = investorAddressRegisterRepository.findAll();
        assertThat(investorAddressRegisterList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvestorAddressRegister.class);
        InvestorAddressRegister investorAddressRegister1 = new InvestorAddressRegister();
        investorAddressRegister1.setId(1L);
        InvestorAddressRegister investorAddressRegister2 = new InvestorAddressRegister();
        investorAddressRegister2.setId(investorAddressRegister1.getId());
        assertThat(investorAddressRegister1).isEqualTo(investorAddressRegister2);
        investorAddressRegister2.setId(2L);
        assertThat(investorAddressRegister1).isNotEqualTo(investorAddressRegister2);
        investorAddressRegister1.setId(null);
        assertThat(investorAddressRegister1).isNotEqualTo(investorAddressRegister2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvestorAddressRegisterDTO.class);
        InvestorAddressRegisterDTO investorAddressRegisterDTO1 = new InvestorAddressRegisterDTO();
        investorAddressRegisterDTO1.setId(1L);
        InvestorAddressRegisterDTO investorAddressRegisterDTO2 = new InvestorAddressRegisterDTO();
        assertThat(investorAddressRegisterDTO1).isNotEqualTo(investorAddressRegisterDTO2);
        investorAddressRegisterDTO2.setId(investorAddressRegisterDTO1.getId());
        assertThat(investorAddressRegisterDTO1).isEqualTo(investorAddressRegisterDTO2);
        investorAddressRegisterDTO2.setId(2L);
        assertThat(investorAddressRegisterDTO1).isNotEqualTo(investorAddressRegisterDTO2);
        investorAddressRegisterDTO1.setId(null);
        assertThat(investorAddressRegisterDTO1).isNotEqualTo(investorAddressRegisterDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(investorAddressRegisterMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(investorAddressRegisterMapper.fromId(null)).isNull();
    }
}
