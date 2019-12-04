package id.tech.cakra.investamart.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;

/**
 * A AccountInstitutionRegister.
 */
@Entity
@Table(name = "account_institution_register")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AccountInstitutionRegister implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 15)
    @Column(name = "sid", length = 15)
    private String sid;

    @Size(max = 100)
    @Column(name = "company_name", length = 100)
    private String companyName;

    @Size(max = 20)
    @Column(name = "bic_code", length = 20)
    private String bicCode;

    @Column(name = "legal_domicile_id")
    private Long legalDomicileId;

    @Size(max = 100)
    @Column(name = "npwp", length = 100)
    private String npwp;

    @Column(name = "npwp_reg_date")
    private LocalDate npwpRegDate;

    @Size(max = 30)
    @Column(name = "skd", length = 30)
    private String skd;

    @Column(name = "skd_exp_date")
    private LocalDate skdExpDate;

    @Size(max = 10)
    @Column(name = "company_establish_place", length = 10)
    private String companyEstablishPlace;

    @Column(name = "company_establish_date")
    private LocalDate companyEstablishDate;

    @Size(max = 1)
    @Column(name = "business_type", length = 1)
    private String businessType;

    @Size(max = 1)
    @Column(name = "company_chracteristic", length = 1)
    private String companyChracteristic;

    @Size(max = 30)
    @Column(name = "fund_source", length = 30)
    private String fundSource;

    @Size(max = 120)
    @Column(name = "fund_source_text", length = 120)
    private String fundSourceText;

    @Size(max = 120)
    @Column(name = "article_association", length = 120)
    private String articleAssociation;

    @Size(max = 120)
    @Column(name = "bussiness_reg_no", length = 120)
    private String bussinessRegNo;

    @Column(name = "financial_asset_1")
    private Double financialAsset1;

    @Column(name = "financial_asset_2")
    private Double financialAsset2;

    @Column(name = "financial_asset_3")
    private Double financialAsset3;

    @Column(name = "operating_profit_1")
    private Double operatingProfit1;

    @Column(name = "operating_profit_2")
    private Double operatingProfit2;

    @Column(name = "operating_profit_3")
    private Double operatingProfit3;

    @Size(max = 100)
    @Column(name = "description", length = 100)
    private String description;

    @Size(max = 100)
    @Column(name = "investment_objective", length = 100)
    private String investmentObjective;

    @Size(max = 15)
    @Column(name = "direct_sid", length = 15)
    private String directSid;

    @Size(max = 1)
    @Column(name = "asset_owner", length = 1)
    private String assetOwner;

    @Size(max = 1)
    @Column(name = "sup_doc_type", length = 1)
    private String supDocType;

    @Column(name = "sup_doc_exp_date")
    private LocalDate supDocExpDate;

    @Column(name = "tax_id")
    private Long taxId;

    @Column(name = "image_cert_article_association")
    private String imageCertArticleAssociation;

    @Column(name = "image_mgmt_structure")
    private String imageMgmtStructure;

    @Column(name = "image_siup")
    private String imageSiup;

    @Column(name = "image_cert_domicile")
    private String imageCertDomicile;

    @Column(name = "image_cert_incorporation")
    private String imageCertIncorporation;

    @Column(name = "image_npwp")
    private String imageNPWP;

    @Column(name = "image_audited_financial")
    private String imageAuditedFinancial;

    @Column(name = "image_share_holder")
    private String imageShareHolder;

    @Column(name = "company_characteristic_text")
    private String companyCharacteristicText;

    @Column(name = "investment_objective_text")
    private String investmentObjectiveText;

    @Column(name = "bussiness_reg_exp_date")
    private LocalDate bussinessRegExpDate;

    @Column(name = "business_type_text")
    private String businessTypeText;

    @Size(max = 1)
    @Column(name = "invest_experience", length = 1)
    private String investExperience;

    @Column(name = "invest_experience_text")
    private String investExperienceText;

    @Column(name = "business_fields")
    private String businessFields;

    @Column(name = "business_fields_text")
    private String businessFieldsText;

    @Size(max = 1)
    @Column(name = "company_domicile_status", length = 1)
    private String companyDomicileStatus;

    @Column(name = "company_domicile_since")
    private LocalDate companyDomicileSince;

    @Column(name = "company_web_address")
    private String companyWebAddress;

    @Column(name = "company_establish_certificate")
    private String companyEstablishCertificate;

    @Column(name = "create_system_date")
    private LocalDate createSystemDate;

    @Column(name = "create_date")
    private ZonedDateTime createDate;

    @Column(name = "create_user_id")
    private Long createUserId;

    @Column(name = "last_modification_system_date")
    private LocalDate lastModificationSystemDate;

    @Column(name = "last_modification_date")
    private ZonedDateTime lastModificationDate;

    @Column(name = "last_modification_user_id")
    private Long lastModificationUserId;

    @OneToOne
    @JoinColumn(unique = true)
    private AccountRegister accountRegister;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSid() {
        return sid;
    }

    public AccountInstitutionRegister sid(String sid) {
        this.sid = sid;
        return this;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getCompanyName() {
        return companyName;
    }

    public AccountInstitutionRegister companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBicCode() {
        return bicCode;
    }

    public AccountInstitutionRegister bicCode(String bicCode) {
        this.bicCode = bicCode;
        return this;
    }

    public void setBicCode(String bicCode) {
        this.bicCode = bicCode;
    }

    public Long getLegalDomicileId() {
        return legalDomicileId;
    }

    public AccountInstitutionRegister legalDomicileId(Long legalDomicileId) {
        this.legalDomicileId = legalDomicileId;
        return this;
    }

    public void setLegalDomicileId(Long legalDomicileId) {
        this.legalDomicileId = legalDomicileId;
    }

    public String getNpwp() {
        return npwp;
    }

    public AccountInstitutionRegister npwp(String npwp) {
        this.npwp = npwp;
        return this;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    public LocalDate getNpwpRegDate() {
        return npwpRegDate;
    }

    public AccountInstitutionRegister npwpRegDate(LocalDate npwpRegDate) {
        this.npwpRegDate = npwpRegDate;
        return this;
    }

    public void setNpwpRegDate(LocalDate npwpRegDate) {
        this.npwpRegDate = npwpRegDate;
    }

    public String getSkd() {
        return skd;
    }

    public AccountInstitutionRegister skd(String skd) {
        this.skd = skd;
        return this;
    }

    public void setSkd(String skd) {
        this.skd = skd;
    }

    public LocalDate getSkdExpDate() {
        return skdExpDate;
    }

    public AccountInstitutionRegister skdExpDate(LocalDate skdExpDate) {
        this.skdExpDate = skdExpDate;
        return this;
    }

    public void setSkdExpDate(LocalDate skdExpDate) {
        this.skdExpDate = skdExpDate;
    }

    public String getCompanyEstablishPlace() {
        return companyEstablishPlace;
    }

    public AccountInstitutionRegister companyEstablishPlace(String companyEstablishPlace) {
        this.companyEstablishPlace = companyEstablishPlace;
        return this;
    }

    public void setCompanyEstablishPlace(String companyEstablishPlace) {
        this.companyEstablishPlace = companyEstablishPlace;
    }

    public LocalDate getCompanyEstablishDate() {
        return companyEstablishDate;
    }

    public AccountInstitutionRegister companyEstablishDate(LocalDate companyEstablishDate) {
        this.companyEstablishDate = companyEstablishDate;
        return this;
    }

    public void setCompanyEstablishDate(LocalDate companyEstablishDate) {
        this.companyEstablishDate = companyEstablishDate;
    }

    public String getBusinessType() {
        return businessType;
    }

    public AccountInstitutionRegister businessType(String businessType) {
        this.businessType = businessType;
        return this;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getCompanyChracteristic() {
        return companyChracteristic;
    }

    public AccountInstitutionRegister companyChracteristic(String companyChracteristic) {
        this.companyChracteristic = companyChracteristic;
        return this;
    }

    public void setCompanyChracteristic(String companyChracteristic) {
        this.companyChracteristic = companyChracteristic;
    }

    public String getFundSource() {
        return fundSource;
    }

    public AccountInstitutionRegister fundSource(String fundSource) {
        this.fundSource = fundSource;
        return this;
    }

    public void setFundSource(String fundSource) {
        this.fundSource = fundSource;
    }

    public String getFundSourceText() {
        return fundSourceText;
    }

    public AccountInstitutionRegister fundSourceText(String fundSourceText) {
        this.fundSourceText = fundSourceText;
        return this;
    }

    public void setFundSourceText(String fundSourceText) {
        this.fundSourceText = fundSourceText;
    }

    public String getArticleAssociation() {
        return articleAssociation;
    }

    public AccountInstitutionRegister articleAssociation(String articleAssociation) {
        this.articleAssociation = articleAssociation;
        return this;
    }

    public void setArticleAssociation(String articleAssociation) {
        this.articleAssociation = articleAssociation;
    }

    public String getBussinessRegNo() {
        return bussinessRegNo;
    }

    public AccountInstitutionRegister bussinessRegNo(String bussinessRegNo) {
        this.bussinessRegNo = bussinessRegNo;
        return this;
    }

    public void setBussinessRegNo(String bussinessRegNo) {
        this.bussinessRegNo = bussinessRegNo;
    }

    public Double getFinancialAsset1() {
        return financialAsset1;
    }

    public AccountInstitutionRegister financialAsset1(Double financialAsset1) {
        this.financialAsset1 = financialAsset1;
        return this;
    }

    public void setFinancialAsset1(Double financialAsset1) {
        this.financialAsset1 = financialAsset1;
    }

    public Double getFinancialAsset2() {
        return financialAsset2;
    }

    public AccountInstitutionRegister financialAsset2(Double financialAsset2) {
        this.financialAsset2 = financialAsset2;
        return this;
    }

    public void setFinancialAsset2(Double financialAsset2) {
        this.financialAsset2 = financialAsset2;
    }

    public Double getFinancialAsset3() {
        return financialAsset3;
    }

    public AccountInstitutionRegister financialAsset3(Double financialAsset3) {
        this.financialAsset3 = financialAsset3;
        return this;
    }

    public void setFinancialAsset3(Double financialAsset3) {
        this.financialAsset3 = financialAsset3;
    }

    public Double getOperatingProfit1() {
        return operatingProfit1;
    }

    public AccountInstitutionRegister operatingProfit1(Double operatingProfit1) {
        this.operatingProfit1 = operatingProfit1;
        return this;
    }

    public void setOperatingProfit1(Double operatingProfit1) {
        this.operatingProfit1 = operatingProfit1;
    }

    public Double getOperatingProfit2() {
        return operatingProfit2;
    }

    public AccountInstitutionRegister operatingProfit2(Double operatingProfit2) {
        this.operatingProfit2 = operatingProfit2;
        return this;
    }

    public void setOperatingProfit2(Double operatingProfit2) {
        this.operatingProfit2 = operatingProfit2;
    }

    public Double getOperatingProfit3() {
        return operatingProfit3;
    }

    public AccountInstitutionRegister operatingProfit3(Double operatingProfit3) {
        this.operatingProfit3 = operatingProfit3;
        return this;
    }

    public void setOperatingProfit3(Double operatingProfit3) {
        this.operatingProfit3 = operatingProfit3;
    }

    public String getDescription() {
        return description;
    }

    public AccountInstitutionRegister description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInvestmentObjective() {
        return investmentObjective;
    }

    public AccountInstitutionRegister investmentObjective(String investmentObjective) {
        this.investmentObjective = investmentObjective;
        return this;
    }

    public void setInvestmentObjective(String investmentObjective) {
        this.investmentObjective = investmentObjective;
    }

    public String getDirectSid() {
        return directSid;
    }

    public AccountInstitutionRegister directSid(String directSid) {
        this.directSid = directSid;
        return this;
    }

    public void setDirectSid(String directSid) {
        this.directSid = directSid;
    }

    public String getAssetOwner() {
        return assetOwner;
    }

    public AccountInstitutionRegister assetOwner(String assetOwner) {
        this.assetOwner = assetOwner;
        return this;
    }

    public void setAssetOwner(String assetOwner) {
        this.assetOwner = assetOwner;
    }

    public String getSupDocType() {
        return supDocType;
    }

    public AccountInstitutionRegister supDocType(String supDocType) {
        this.supDocType = supDocType;
        return this;
    }

    public void setSupDocType(String supDocType) {
        this.supDocType = supDocType;
    }

    public LocalDate getSupDocExpDate() {
        return supDocExpDate;
    }

    public AccountInstitutionRegister supDocExpDate(LocalDate supDocExpDate) {
        this.supDocExpDate = supDocExpDate;
        return this;
    }

    public void setSupDocExpDate(LocalDate supDocExpDate) {
        this.supDocExpDate = supDocExpDate;
    }

    public Long getTaxId() {
        return taxId;
    }

    public AccountInstitutionRegister taxId(Long taxId) {
        this.taxId = taxId;
        return this;
    }

    public void setTaxId(Long taxId) {
        this.taxId = taxId;
    }

    public String getImageCertArticleAssociation() {
        return imageCertArticleAssociation;
    }

    public AccountInstitutionRegister imageCertArticleAssociation(String imageCertArticleAssociation) {
        this.imageCertArticleAssociation = imageCertArticleAssociation;
        return this;
    }

    public void setImageCertArticleAssociation(String imageCertArticleAssociation) {
        this.imageCertArticleAssociation = imageCertArticleAssociation;
    }

    public String getImageMgmtStructure() {
        return imageMgmtStructure;
    }

    public AccountInstitutionRegister imageMgmtStructure(String imageMgmtStructure) {
        this.imageMgmtStructure = imageMgmtStructure;
        return this;
    }

    public void setImageMgmtStructure(String imageMgmtStructure) {
        this.imageMgmtStructure = imageMgmtStructure;
    }

    public String getImageSiup() {
        return imageSiup;
    }

    public AccountInstitutionRegister imageSiup(String imageSiup) {
        this.imageSiup = imageSiup;
        return this;
    }

    public void setImageSiup(String imageSiup) {
        this.imageSiup = imageSiup;
    }

    public String getImageCertDomicile() {
        return imageCertDomicile;
    }

    public AccountInstitutionRegister imageCertDomicile(String imageCertDomicile) {
        this.imageCertDomicile = imageCertDomicile;
        return this;
    }

    public void setImageCertDomicile(String imageCertDomicile) {
        this.imageCertDomicile = imageCertDomicile;
    }

    public String getImageCertIncorporation() {
        return imageCertIncorporation;
    }

    public AccountInstitutionRegister imageCertIncorporation(String imageCertIncorporation) {
        this.imageCertIncorporation = imageCertIncorporation;
        return this;
    }

    public void setImageCertIncorporation(String imageCertIncorporation) {
        this.imageCertIncorporation = imageCertIncorporation;
    }

    public String getImageNPWP() {
        return imageNPWP;
    }

    public AccountInstitutionRegister imageNPWP(String imageNPWP) {
        this.imageNPWP = imageNPWP;
        return this;
    }

    public void setImageNPWP(String imageNPWP) {
        this.imageNPWP = imageNPWP;
    }

    public String getImageAuditedFinancial() {
        return imageAuditedFinancial;
    }

    public AccountInstitutionRegister imageAuditedFinancial(String imageAuditedFinancial) {
        this.imageAuditedFinancial = imageAuditedFinancial;
        return this;
    }

    public void setImageAuditedFinancial(String imageAuditedFinancial) {
        this.imageAuditedFinancial = imageAuditedFinancial;
    }

    public String getImageShareHolder() {
        return imageShareHolder;
    }

    public AccountInstitutionRegister imageShareHolder(String imageShareHolder) {
        this.imageShareHolder = imageShareHolder;
        return this;
    }

    public void setImageShareHolder(String imageShareHolder) {
        this.imageShareHolder = imageShareHolder;
    }

    public String getCompanyCharacteristicText() {
        return companyCharacteristicText;
    }

    public AccountInstitutionRegister companyCharacteristicText(String companyCharacteristicText) {
        this.companyCharacteristicText = companyCharacteristicText;
        return this;
    }

    public void setCompanyCharacteristicText(String companyCharacteristicText) {
        this.companyCharacteristicText = companyCharacteristicText;
    }

    public String getInvestmentObjectiveText() {
        return investmentObjectiveText;
    }

    public AccountInstitutionRegister investmentObjectiveText(String investmentObjectiveText) {
        this.investmentObjectiveText = investmentObjectiveText;
        return this;
    }

    public void setInvestmentObjectiveText(String investmentObjectiveText) {
        this.investmentObjectiveText = investmentObjectiveText;
    }

    public LocalDate getBussinessRegExpDate() {
        return bussinessRegExpDate;
    }

    public AccountInstitutionRegister bussinessRegExpDate(LocalDate bussinessRegExpDate) {
        this.bussinessRegExpDate = bussinessRegExpDate;
        return this;
    }

    public void setBussinessRegExpDate(LocalDate bussinessRegExpDate) {
        this.bussinessRegExpDate = bussinessRegExpDate;
    }

    public String getBusinessTypeText() {
        return businessTypeText;
    }

    public AccountInstitutionRegister businessTypeText(String businessTypeText) {
        this.businessTypeText = businessTypeText;
        return this;
    }

    public void setBusinessTypeText(String businessTypeText) {
        this.businessTypeText = businessTypeText;
    }

    public String getInvestExperience() {
        return investExperience;
    }

    public AccountInstitutionRegister investExperience(String investExperience) {
        this.investExperience = investExperience;
        return this;
    }

    public void setInvestExperience(String investExperience) {
        this.investExperience = investExperience;
    }

    public String getInvestExperienceText() {
        return investExperienceText;
    }

    public AccountInstitutionRegister investExperienceText(String investExperienceText) {
        this.investExperienceText = investExperienceText;
        return this;
    }

    public void setInvestExperienceText(String investExperienceText) {
        this.investExperienceText = investExperienceText;
    }

    public String getBusinessFields() {
        return businessFields;
    }

    public AccountInstitutionRegister businessFields(String businessFields) {
        this.businessFields = businessFields;
        return this;
    }

    public void setBusinessFields(String businessFields) {
        this.businessFields = businessFields;
    }

    public String getBusinessFieldsText() {
        return businessFieldsText;
    }

    public AccountInstitutionRegister businessFieldsText(String businessFieldsText) {
        this.businessFieldsText = businessFieldsText;
        return this;
    }

    public void setBusinessFieldsText(String businessFieldsText) {
        this.businessFieldsText = businessFieldsText;
    }

    public String getCompanyDomicileStatus() {
        return companyDomicileStatus;
    }

    public AccountInstitutionRegister companyDomicileStatus(String companyDomicileStatus) {
        this.companyDomicileStatus = companyDomicileStatus;
        return this;
    }

    public void setCompanyDomicileStatus(String companyDomicileStatus) {
        this.companyDomicileStatus = companyDomicileStatus;
    }

    public LocalDate getCompanyDomicileSince() {
        return companyDomicileSince;
    }

    public AccountInstitutionRegister companyDomicileSince(LocalDate companyDomicileSince) {
        this.companyDomicileSince = companyDomicileSince;
        return this;
    }

    public void setCompanyDomicileSince(LocalDate companyDomicileSince) {
        this.companyDomicileSince = companyDomicileSince;
    }

    public String getCompanyWebAddress() {
        return companyWebAddress;
    }

    public AccountInstitutionRegister companyWebAddress(String companyWebAddress) {
        this.companyWebAddress = companyWebAddress;
        return this;
    }

    public void setCompanyWebAddress(String companyWebAddress) {
        this.companyWebAddress = companyWebAddress;
    }

    public String getCompanyEstablishCertificate() {
        return companyEstablishCertificate;
    }

    public AccountInstitutionRegister companyEstablishCertificate(String companyEstablishCertificate) {
        this.companyEstablishCertificate = companyEstablishCertificate;
        return this;
    }

    public void setCompanyEstablishCertificate(String companyEstablishCertificate) {
        this.companyEstablishCertificate = companyEstablishCertificate;
    }

    public LocalDate getCreateSystemDate() {
        return createSystemDate;
    }

    public AccountInstitutionRegister createSystemDate(LocalDate createSystemDate) {
        this.createSystemDate = createSystemDate;
        return this;
    }

    public void setCreateSystemDate(LocalDate createSystemDate) {
        this.createSystemDate = createSystemDate;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public AccountInstitutionRegister createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public AccountInstitutionRegister createUserId(Long createUserId) {
        this.createUserId = createUserId;
        return this;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public LocalDate getLastModificationSystemDate() {
        return lastModificationSystemDate;
    }

    public AccountInstitutionRegister lastModificationSystemDate(LocalDate lastModificationSystemDate) {
        this.lastModificationSystemDate = lastModificationSystemDate;
        return this;
    }

    public void setLastModificationSystemDate(LocalDate lastModificationSystemDate) {
        this.lastModificationSystemDate = lastModificationSystemDate;
    }

    public ZonedDateTime getLastModificationDate() {
        return lastModificationDate;
    }

    public AccountInstitutionRegister lastModificationDate(ZonedDateTime lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
        return this;
    }

    public void setLastModificationDate(ZonedDateTime lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public Long getLastModificationUserId() {
        return lastModificationUserId;
    }

    public AccountInstitutionRegister lastModificationUserId(Long lastModificationUserId) {
        this.lastModificationUserId = lastModificationUserId;
        return this;
    }

    public void setLastModificationUserId(Long lastModificationUserId) {
        this.lastModificationUserId = lastModificationUserId;
    }

    public AccountRegister getAccountRegister() {
        return accountRegister;
    }

    public AccountInstitutionRegister accountRegister(AccountRegister accountRegister) {
        this.accountRegister = accountRegister;
        return this;
    }

    public void setAccountRegister(AccountRegister accountRegister) {
        this.accountRegister = accountRegister;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AccountInstitutionRegister)) {
            return false;
        }
        return id != null && id.equals(((AccountInstitutionRegister) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AccountInstitutionRegister{" +
            "id=" + getId() +
            ", sid='" + getSid() + "'" +
            ", companyName='" + getCompanyName() + "'" +
            ", bicCode='" + getBicCode() + "'" +
            ", legalDomicileId=" + getLegalDomicileId() +
            ", npwp='" + getNpwp() + "'" +
            ", npwpRegDate='" + getNpwpRegDate() + "'" +
            ", skd='" + getSkd() + "'" +
            ", skdExpDate='" + getSkdExpDate() + "'" +
            ", companyEstablishPlace='" + getCompanyEstablishPlace() + "'" +
            ", companyEstablishDate='" + getCompanyEstablishDate() + "'" +
            ", businessType='" + getBusinessType() + "'" +
            ", companyChracteristic='" + getCompanyChracteristic() + "'" +
            ", fundSource='" + getFundSource() + "'" +
            ", fundSourceText='" + getFundSourceText() + "'" +
            ", articleAssociation='" + getArticleAssociation() + "'" +
            ", bussinessRegNo='" + getBussinessRegNo() + "'" +
            ", financialAsset1=" + getFinancialAsset1() +
            ", financialAsset2=" + getFinancialAsset2() +
            ", financialAsset3=" + getFinancialAsset3() +
            ", operatingProfit1=" + getOperatingProfit1() +
            ", operatingProfit2=" + getOperatingProfit2() +
            ", operatingProfit3=" + getOperatingProfit3() +
            ", description='" + getDescription() + "'" +
            ", investmentObjective='" + getInvestmentObjective() + "'" +
            ", directSid='" + getDirectSid() + "'" +
            ", assetOwner='" + getAssetOwner() + "'" +
            ", supDocType='" + getSupDocType() + "'" +
            ", supDocExpDate='" + getSupDocExpDate() + "'" +
            ", taxId=" + getTaxId() +
            ", imageCertArticleAssociation='" + getImageCertArticleAssociation() + "'" +
            ", imageMgmtStructure='" + getImageMgmtStructure() + "'" +
            ", imageSiup='" + getImageSiup() + "'" +
            ", imageCertDomicile='" + getImageCertDomicile() + "'" +
            ", imageCertIncorporation='" + getImageCertIncorporation() + "'" +
            ", imageNPWP='" + getImageNPWP() + "'" +
            ", imageAuditedFinancial='" + getImageAuditedFinancial() + "'" +
            ", imageShareHolder='" + getImageShareHolder() + "'" +
            ", companyCharacteristicText='" + getCompanyCharacteristicText() + "'" +
            ", investmentObjectiveText='" + getInvestmentObjectiveText() + "'" +
            ", bussinessRegExpDate='" + getBussinessRegExpDate() + "'" +
            ", businessTypeText='" + getBusinessTypeText() + "'" +
            ", investExperience='" + getInvestExperience() + "'" +
            ", investExperienceText='" + getInvestExperienceText() + "'" +
            ", businessFields='" + getBusinessFields() + "'" +
            ", businessFieldsText='" + getBusinessFieldsText() + "'" +
            ", companyDomicileStatus='" + getCompanyDomicileStatus() + "'" +
            ", companyDomicileSince='" + getCompanyDomicileSince() + "'" +
            ", companyWebAddress='" + getCompanyWebAddress() + "'" +
            ", companyEstablishCertificate='" + getCompanyEstablishCertificate() + "'" +
            ", createSystemDate='" + getCreateSystemDate() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUserId=" + getCreateUserId() +
            ", lastModificationSystemDate='" + getLastModificationSystemDate() + "'" +
            ", lastModificationDate='" + getLastModificationDate() + "'" +
            ", lastModificationUserId=" + getLastModificationUserId() +
            "}";
    }
}
