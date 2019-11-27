package id.tech.cakra.investamart.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;

/**
 * A InvestorAddressRegister.
 */
@Entity
@Table(name = "investor_address_register")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class InvestorAddressRegister implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 1)
    @Column(name = "address_type", length = 1)
    private String addressType;

    @Size(max = 60)
    @Column(name = "address_1", length = 60)
    private String address1;

    @Size(max = 60)
    @Column(name = "address_2", length = 60)
    private String address2;

    @Size(max = 60)
    @Column(name = "address_3", length = 60)
    private String address3;

    @Size(max = 5)
    @Column(name = "postal_code", length = 5)
    private String postalCode;

    @Size(max = 30)
    @Column(name = "phone", length = 30)
    private String phone;

    @Size(max = 30)
    @Column(name = "mobile_phone", length = 30)
    private String mobilePhone;

    @Size(max = 256)
    @Column(name = "email", length = 256)
    private String email;

    @Size(max = 30)
    @Column(name = "fax", length = 30)
    private String fax;

    @Column(name = "country_id")
    private Long countryId;

    @Column(name = "province_id")
    private Long provinceId;

    @Column(name = "city_id")
    private Long cityId;

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

    @ManyToOne
    @JsonIgnoreProperties("investorAddressRegisters")
    private AccountRegister accountRegister;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressType() {
        return addressType;
    }

    public InvestorAddressRegister addressType(String addressType) {
        this.addressType = addressType;
        return this;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getAddress1() {
        return address1;
    }

    public InvestorAddressRegister address1(String address1) {
        this.address1 = address1;
        return this;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public InvestorAddressRegister address2(String address2) {
        this.address2 = address2;
        return this;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public InvestorAddressRegister address3(String address3) {
        this.address3 = address3;
        return this;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public InvestorAddressRegister postalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public InvestorAddressRegister phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public InvestorAddressRegister mobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public InvestorAddressRegister email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public InvestorAddressRegister fax(String fax) {
        this.fax = fax;
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Long getCountryId() {
        return countryId;
    }

    public InvestorAddressRegister countryId(Long countryId) {
        this.countryId = countryId;
        return this;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public InvestorAddressRegister provinceId(Long provinceId) {
        this.provinceId = provinceId;
        return this;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getCityId() {
        return cityId;
    }

    public InvestorAddressRegister cityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public LocalDate getCreateSystemDate() {
        return createSystemDate;
    }

    public InvestorAddressRegister createSystemDate(LocalDate createSystemDate) {
        this.createSystemDate = createSystemDate;
        return this;
    }

    public void setCreateSystemDate(LocalDate createSystemDate) {
        this.createSystemDate = createSystemDate;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public InvestorAddressRegister createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public InvestorAddressRegister createUserId(Long createUserId) {
        this.createUserId = createUserId;
        return this;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public LocalDate getLastModificationSystemDate() {
        return lastModificationSystemDate;
    }

    public InvestorAddressRegister lastModificationSystemDate(LocalDate lastModificationSystemDate) {
        this.lastModificationSystemDate = lastModificationSystemDate;
        return this;
    }

    public void setLastModificationSystemDate(LocalDate lastModificationSystemDate) {
        this.lastModificationSystemDate = lastModificationSystemDate;
    }

    public ZonedDateTime getLastModificationDate() {
        return lastModificationDate;
    }

    public InvestorAddressRegister lastModificationDate(ZonedDateTime lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
        return this;
    }

    public void setLastModificationDate(ZonedDateTime lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public Long getLastModificationUserId() {
        return lastModificationUserId;
    }

    public InvestorAddressRegister lastModificationUserId(Long lastModificationUserId) {
        this.lastModificationUserId = lastModificationUserId;
        return this;
    }

    public void setLastModificationUserId(Long lastModificationUserId) {
        this.lastModificationUserId = lastModificationUserId;
    }

    public AccountRegister getAccountRegister() {
        return accountRegister;
    }

    public InvestorAddressRegister accountRegister(AccountRegister accountRegister) {
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
        if (!(o instanceof InvestorAddressRegister)) {
            return false;
        }
        return id != null && id.equals(((InvestorAddressRegister) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "InvestorAddressRegister{" +
            "id=" + getId() +
            ", addressType='" + getAddressType() + "'" +
            ", address1='" + getAddress1() + "'" +
            ", address2='" + getAddress2() + "'" +
            ", address3='" + getAddress3() + "'" +
            ", postalCode='" + getPostalCode() + "'" +
            ", phone='" + getPhone() + "'" +
            ", mobilePhone='" + getMobilePhone() + "'" +
            ", email='" + getEmail() + "'" +
            ", fax='" + getFax() + "'" +
            ", countryId=" + getCountryId() +
            ", provinceId=" + getProvinceId() +
            ", cityId=" + getCityId() +
            ", createSystemDate='" + getCreateSystemDate() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUserId=" + getCreateUserId() +
            ", lastModificationSystemDate='" + getLastModificationSystemDate() + "'" +
            ", lastModificationDate='" + getLastModificationDate() + "'" +
            ", lastModificationUserId=" + getLastModificationUserId() +
            "}";
    }
}
