package id.tech.cakra.investamart.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;

/**
 * A AccountIndividuRegister.
 */
@Entity
@Table(name = "account_individu_register")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AccountIndividuRegister implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 15)
    @Column(name = "sid", length = 15)
    private String sid;

    @Size(max = 35)
    @Column(name = "first_name", length = 35)
    private String firstName;

    @Size(max = 35)
    @Column(name = "middle_name", length = 35)
    private String middleName;

    @Size(max = 35)
    @Column(name = "last_name", length = 35)
    private String lastName;

    @Column(name = "nationality_id")
    private Long nationalityId;

    @Size(max = 25)
    @Column(name = "ktp", length = 25)
    private String ktp;

    @Column(name = "ktp_exp_date")
    private LocalDate ktpExpDate;

    @Size(max = 15)
    @Column(name = "npwp", length = 15)
    private String npwp;

    @Column(name = "npwp_reg_date")
    private LocalDate npwpRegDate;

    @Size(max = 25)
    @Column(name = "passport", length = 25)
    private String passport;

    @Column(name = "passport_exp_date")
    private LocalDate passportExpDate;

    @Size(max = 30)
    @Column(name = "kitas", length = 30)
    private String kitas;

    @Column(name = "kitas_exp_date")
    private LocalDate kitasExpDate;

    @Size(max = 100)
    @Column(name = "birth_place", length = 100)
    private String birthPlace;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Size(max = 1)
    @Column(name = "sex", length = 1)
    private String sex;

    @Size(max = 1)
    @Column(name = "marital_status", length = 1)
    private String maritalStatus;

    @Size(max = 100)
    @Column(name = "spouse_name", length = 100)
    private String spouseName;

    @Size(max = 120)
    @Column(name = "heir", length = 120)
    private String heir;

    @Size(max = 120)
    @Column(name = "heir_ralation", length = 120)
    private String heirRalation;

    @Size(max = 1)
    @Column(name = "education_background", length = 1)
    private String educationBackground;

    @Size(max = 1)
    @Column(name = "occupation", length = 1)
    private String occupation;

    @Size(max = 120)
    @Column(name = "nature_of_business", length = 120)
    private String natureOfBusiness;

    @Column(name = "annual_income")
    private Double annualIncome;

    @Size(max = 30)
    @Column(name = "fund_source", length = 30)
    private String fundSource;

    @Size(max = 120)
    @Column(name = "fund_source_text", length = 120)
    private String fundSourceText;

    @Size(max = 100)
    @Column(name = "description", length = 100)
    private String description;

    @Size(max = 10)
    @Column(name = "investment_objective", length = 10)
    private String investmentObjective;

    @Size(max = 100)
    @Column(name = "mother_maiden_name", length = 100)
    private String motherMaidenName;

    @Size(max = 15)
    @Column(name = "direct_sid", length = 15)
    private String directSid;

    @Size(max = 1)
    @Column(name = "asset_owner", length = 1)
    private String assetOwner;

    @Column(name = "auth_person_ktp_reg_date")
    private LocalDate authPersonKtpRegDate;

    @Column(name = "tax_id")
    private Long taxId;

    @Column(name = "image_ktp")
    private String imageKtp;

    @Column(name = "image_npwp")
    private String imageNpwp;

    @Column(name = "image_selfie_ktp")
    private String imageSelfieKtp;

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

    public AccountIndividuRegister sid(String sid) {
        this.sid = sid;
        return this;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getFirstName() {
        return firstName;
    }

    public AccountIndividuRegister firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public AccountIndividuRegister middleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public AccountIndividuRegister lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getNationalityId() {
        return nationalityId;
    }

    public AccountIndividuRegister nationalityId(Long nationalityId) {
        this.nationalityId = nationalityId;
        return this;
    }

    public void setNationalityId(Long nationalityId) {
        this.nationalityId = nationalityId;
    }

    public String getKtp() {
        return ktp;
    }

    public AccountIndividuRegister ktp(String ktp) {
        this.ktp = ktp;
        return this;
    }

    public void setKtp(String ktp) {
        this.ktp = ktp;
    }

    public LocalDate getKtpExpDate() {
        return ktpExpDate;
    }

    public AccountIndividuRegister ktpExpDate(LocalDate ktpExpDate) {
        this.ktpExpDate = ktpExpDate;
        return this;
    }

    public void setKtpExpDate(LocalDate ktpExpDate) {
        this.ktpExpDate = ktpExpDate;
    }

    public String getNpwp() {
        return npwp;
    }

    public AccountIndividuRegister npwp(String npwp) {
        this.npwp = npwp;
        return this;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    public LocalDate getNpwpRegDate() {
        return npwpRegDate;
    }

    public AccountIndividuRegister npwpRegDate(LocalDate npwpRegDate) {
        this.npwpRegDate = npwpRegDate;
        return this;
    }

    public void setNpwpRegDate(LocalDate npwpRegDate) {
        this.npwpRegDate = npwpRegDate;
    }

    public String getPassport() {
        return passport;
    }

    public AccountIndividuRegister passport(String passport) {
        this.passport = passport;
        return this;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public LocalDate getPassportExpDate() {
        return passportExpDate;
    }

    public AccountIndividuRegister passportExpDate(LocalDate passportExpDate) {
        this.passportExpDate = passportExpDate;
        return this;
    }

    public void setPassportExpDate(LocalDate passportExpDate) {
        this.passportExpDate = passportExpDate;
    }

    public String getKitas() {
        return kitas;
    }

    public AccountIndividuRegister kitas(String kitas) {
        this.kitas = kitas;
        return this;
    }

    public void setKitas(String kitas) {
        this.kitas = kitas;
    }

    public LocalDate getKitasExpDate() {
        return kitasExpDate;
    }

    public AccountIndividuRegister kitasExpDate(LocalDate kitasExpDate) {
        this.kitasExpDate = kitasExpDate;
        return this;
    }

    public void setKitasExpDate(LocalDate kitasExpDate) {
        this.kitasExpDate = kitasExpDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public AccountIndividuRegister birthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
        return this;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public AccountIndividuRegister birthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getSex() {
        return sex;
    }

    public AccountIndividuRegister sex(String sex) {
        this.sex = sex;
        return this;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public AccountIndividuRegister maritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
        return this;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public AccountIndividuRegister spouseName(String spouseName) {
        this.spouseName = spouseName;
        return this;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    public String getHeir() {
        return heir;
    }

    public AccountIndividuRegister heir(String heir) {
        this.heir = heir;
        return this;
    }

    public void setHeir(String heir) {
        this.heir = heir;
    }

    public String getHeirRalation() {
        return heirRalation;
    }

    public AccountIndividuRegister heirRalation(String heirRalation) {
        this.heirRalation = heirRalation;
        return this;
    }

    public void setHeirRalation(String heirRalation) {
        this.heirRalation = heirRalation;
    }

    public String getEducationBackground() {
        return educationBackground;
    }

    public AccountIndividuRegister educationBackground(String educationBackground) {
        this.educationBackground = educationBackground;
        return this;
    }

    public void setEducationBackground(String educationBackground) {
        this.educationBackground = educationBackground;
    }

    public String getOccupation() {
        return occupation;
    }

    public AccountIndividuRegister occupation(String occupation) {
        this.occupation = occupation;
        return this;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getNatureOfBusiness() {
        return natureOfBusiness;
    }

    public AccountIndividuRegister natureOfBusiness(String natureOfBusiness) {
        this.natureOfBusiness = natureOfBusiness;
        return this;
    }

    public void setNatureOfBusiness(String natureOfBusiness) {
        this.natureOfBusiness = natureOfBusiness;
    }

    public Double getAnnualIncome() {
        return annualIncome;
    }

    public AccountIndividuRegister annualIncome(Double annualIncome) {
        this.annualIncome = annualIncome;
        return this;
    }

    public void setAnnualIncome(Double annualIncome) {
        this.annualIncome = annualIncome;
    }

    public String getFundSource() {
        return fundSource;
    }

    public AccountIndividuRegister fundSource(String fundSource) {
        this.fundSource = fundSource;
        return this;
    }

    public void setFundSource(String fundSource) {
        this.fundSource = fundSource;
    }

    public String getFundSourceText() {
        return fundSourceText;
    }

    public AccountIndividuRegister fundSourceText(String fundSourceText) {
        this.fundSourceText = fundSourceText;
        return this;
    }

    public void setFundSourceText(String fundSourceText) {
        this.fundSourceText = fundSourceText;
    }

    public String getDescription() {
        return description;
    }

    public AccountIndividuRegister description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInvestmentObjective() {
        return investmentObjective;
    }

    public AccountIndividuRegister investmentObjective(String investmentObjective) {
        this.investmentObjective = investmentObjective;
        return this;
    }

    public void setInvestmentObjective(String investmentObjective) {
        this.investmentObjective = investmentObjective;
    }

    public String getMotherMaidenName() {
        return motherMaidenName;
    }

    public AccountIndividuRegister motherMaidenName(String motherMaidenName) {
        this.motherMaidenName = motherMaidenName;
        return this;
    }

    public void setMotherMaidenName(String motherMaidenName) {
        this.motherMaidenName = motherMaidenName;
    }

    public String getDirectSid() {
        return directSid;
    }

    public AccountIndividuRegister directSid(String directSid) {
        this.directSid = directSid;
        return this;
    }

    public void setDirectSid(String directSid) {
        this.directSid = directSid;
    }

    public String getAssetOwner() {
        return assetOwner;
    }

    public AccountIndividuRegister assetOwner(String assetOwner) {
        this.assetOwner = assetOwner;
        return this;
    }

    public void setAssetOwner(String assetOwner) {
        this.assetOwner = assetOwner;
    }

    public LocalDate getAuthPersonKtpRegDate() {
        return authPersonKtpRegDate;
    }

    public AccountIndividuRegister authPersonKtpRegDate(LocalDate authPersonKtpRegDate) {
        this.authPersonKtpRegDate = authPersonKtpRegDate;
        return this;
    }

    public void setAuthPersonKtpRegDate(LocalDate authPersonKtpRegDate) {
        this.authPersonKtpRegDate = authPersonKtpRegDate;
    }

    public Long getTaxId() {
        return taxId;
    }

    public AccountIndividuRegister taxId(Long taxId) {
        this.taxId = taxId;
        return this;
    }

    public void setTaxId(Long taxId) {
        this.taxId = taxId;
    }

    public String getImageKtp() {
        return imageKtp;
    }

    public AccountIndividuRegister imageKtp(String imageKtp) {
        this.imageKtp = imageKtp;
        return this;
    }

    public void setImageKtp(String imageKtp) {
        this.imageKtp = imageKtp;
    }

    public String getImageNpwp() {
        return imageNpwp;
    }

    public AccountIndividuRegister imageNpwp(String imageNpwp) {
        this.imageNpwp = imageNpwp;
        return this;
    }

    public void setImageNpwp(String imageNpwp) {
        this.imageNpwp = imageNpwp;
    }

    public String getImageSelfieKtp() {
        return imageSelfieKtp;
    }

    public AccountIndividuRegister imageSelfieKtp(String imageSelfieKtp) {
        this.imageSelfieKtp = imageSelfieKtp;
        return this;
    }

    public void setImageSelfieKtp(String imageSelfieKtp) {
        this.imageSelfieKtp = imageSelfieKtp;
    }

    public LocalDate getCreateSystemDate() {
        return createSystemDate;
    }

    public AccountIndividuRegister createSystemDate(LocalDate createSystemDate) {
        this.createSystemDate = createSystemDate;
        return this;
    }

    public void setCreateSystemDate(LocalDate createSystemDate) {
        this.createSystemDate = createSystemDate;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public AccountIndividuRegister createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public AccountIndividuRegister createUserId(Long createUserId) {
        this.createUserId = createUserId;
        return this;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public LocalDate getLastModificationSystemDate() {
        return lastModificationSystemDate;
    }

    public AccountIndividuRegister lastModificationSystemDate(LocalDate lastModificationSystemDate) {
        this.lastModificationSystemDate = lastModificationSystemDate;
        return this;
    }

    public void setLastModificationSystemDate(LocalDate lastModificationSystemDate) {
        this.lastModificationSystemDate = lastModificationSystemDate;
    }

    public ZonedDateTime getLastModificationDate() {
        return lastModificationDate;
    }

    public AccountIndividuRegister lastModificationDate(ZonedDateTime lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
        return this;
    }

    public void setLastModificationDate(ZonedDateTime lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public Long getLastModificationUserId() {
        return lastModificationUserId;
    }

    public AccountIndividuRegister lastModificationUserId(Long lastModificationUserId) {
        this.lastModificationUserId = lastModificationUserId;
        return this;
    }

    public void setLastModificationUserId(Long lastModificationUserId) {
        this.lastModificationUserId = lastModificationUserId;
    }

    public AccountRegister getAccountRegister() {
        return accountRegister;
    }

    public AccountIndividuRegister accountRegister(AccountRegister accountRegister) {
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
        if (!(o instanceof AccountIndividuRegister)) {
            return false;
        }
        return id != null && id.equals(((AccountIndividuRegister) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AccountIndividuRegister{" +
            "id=" + getId() +
            ", sid='" + getSid() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", middleName='" + getMiddleName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", nationalityId=" + getNationalityId() +
            ", ktp='" + getKtp() + "'" +
            ", ktpExpDate='" + getKtpExpDate() + "'" +
            ", npwp='" + getNpwp() + "'" +
            ", npwpRegDate='" + getNpwpRegDate() + "'" +
            ", passport='" + getPassport() + "'" +
            ", passportExpDate='" + getPassportExpDate() + "'" +
            ", kitas='" + getKitas() + "'" +
            ", kitasExpDate='" + getKitasExpDate() + "'" +
            ", birthPlace='" + getBirthPlace() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", sex='" + getSex() + "'" +
            ", maritalStatus='" + getMaritalStatus() + "'" +
            ", spouseName='" + getSpouseName() + "'" +
            ", heir='" + getHeir() + "'" +
            ", heirRalation='" + getHeirRalation() + "'" +
            ", educationBackground='" + getEducationBackground() + "'" +
            ", occupation='" + getOccupation() + "'" +
            ", natureOfBusiness='" + getNatureOfBusiness() + "'" +
            ", annualIncome=" + getAnnualIncome() +
            ", fundSource='" + getFundSource() + "'" +
            ", fundSourceText='" + getFundSourceText() + "'" +
            ", description='" + getDescription() + "'" +
            ", investmentObjective='" + getInvestmentObjective() + "'" +
            ", motherMaidenName='" + getMotherMaidenName() + "'" +
            ", directSid='" + getDirectSid() + "'" +
            ", assetOwner='" + getAssetOwner() + "'" +
            ", authPersonKtpRegDate='" + getAuthPersonKtpRegDate() + "'" +
            ", taxId=" + getTaxId() +
            ", imageKtp='" + getImageKtp() + "'" +
            ", imageNpwp='" + getImageNpwp() + "'" +
            ", imageSelfieKtp='" + getImageSelfieKtp() + "'" +
            ", createSystemDate='" + getCreateSystemDate() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUserId=" + getCreateUserId() +
            ", lastModificationSystemDate='" + getLastModificationSystemDate() + "'" +
            ", lastModificationDate='" + getLastModificationDate() + "'" +
            ", lastModificationUserId=" + getLastModificationUserId() +
            "}";
    }
}
