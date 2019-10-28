package id.tech.cakra.investamart.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * A AccountRegister.
 */
@Entity
@Table(name = "account_register")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AccountRegister implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 10)
    @Column(name = "register_code", length = 10)
    private String registerCode;

    @Size(max = 10)
    @Column(name = "account_name", length = 10)
    private String accountName;

    @Size(max = 1)
    @Column(name = "account_type", length = 1)
    private String accountType;

    @Column(name = "account_angle")
    private Boolean accountAngle;

    @Size(max = 4)
    @Column(name = "ksei_client_code", length = 4)
    private String kseiClientCode;

    @Size(max = 15)
    @Column(name = "ksei_subrek", length = 15)
    private String kseiSubrek;

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
    private AccountIndividuRegister accountIndividuRegister;

    @OneToOne
    @JoinColumn(unique = true)
    private AccountInstitutionRegister accountInstitutionRegister;

    @OneToMany(mappedBy = "accountRegister")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AccountAuthorizeRegister> accountAuthorizeRegisters = new HashSet<>();

    @OneToMany(mappedBy = "accountRegister")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<InvestorAddressRegister> investorAddressRegisters = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegisterCode() {
        return registerCode;
    }

    public AccountRegister registerCode(String registerCode) {
        this.registerCode = registerCode;
        return this;
    }

    public void setRegisterCode(String registerCode) {
        this.registerCode = registerCode;
    }

    public String getAccountName() {
        return accountName;
    }

    public AccountRegister accountName(String accountName) {
        this.accountName = accountName;
        return this;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public AccountRegister accountType(String accountType) {
        this.accountType = accountType;
        return this;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Boolean isAccountAngle() {
        return accountAngle;
    }

    public AccountRegister accountAngle(Boolean accountAngle) {
        this.accountAngle = accountAngle;
        return this;
    }

    public void setAccountAngle(Boolean accountAngle) {
        this.accountAngle = accountAngle;
    }

    public String getKseiClientCode() {
        return kseiClientCode;
    }

    public AccountRegister kseiClientCode(String kseiClientCode) {
        this.kseiClientCode = kseiClientCode;
        return this;
    }

    public void setKseiClientCode(String kseiClientCode) {
        this.kseiClientCode = kseiClientCode;
    }

    public String getKseiSubrek() {
        return kseiSubrek;
    }

    public AccountRegister kseiSubrek(String kseiSubrek) {
        this.kseiSubrek = kseiSubrek;
        return this;
    }

    public void setKseiSubrek(String kseiSubrek) {
        this.kseiSubrek = kseiSubrek;
    }

    public LocalDate getCreateSystemDate() {
        return createSystemDate;
    }

    public AccountRegister createSystemDate(LocalDate createSystemDate) {
        this.createSystemDate = createSystemDate;
        return this;
    }

    public void setCreateSystemDate(LocalDate createSystemDate) {
        this.createSystemDate = createSystemDate;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public AccountRegister createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public AccountRegister createUserId(Long createUserId) {
        this.createUserId = createUserId;
        return this;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public LocalDate getLastModificationSystemDate() {
        return lastModificationSystemDate;
    }

    public AccountRegister lastModificationSystemDate(LocalDate lastModificationSystemDate) {
        this.lastModificationSystemDate = lastModificationSystemDate;
        return this;
    }

    public void setLastModificationSystemDate(LocalDate lastModificationSystemDate) {
        this.lastModificationSystemDate = lastModificationSystemDate;
    }

    public ZonedDateTime getLastModificationDate() {
        return lastModificationDate;
    }

    public AccountRegister lastModificationDate(ZonedDateTime lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
        return this;
    }

    public void setLastModificationDate(ZonedDateTime lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public Long getLastModificationUserId() {
        return lastModificationUserId;
    }

    public AccountRegister lastModificationUserId(Long lastModificationUserId) {
        this.lastModificationUserId = lastModificationUserId;
        return this;
    }

    public void setLastModificationUserId(Long lastModificationUserId) {
        this.lastModificationUserId = lastModificationUserId;
    }

    public AccountIndividuRegister getAccountIndividuRegister() {
        return accountIndividuRegister;
    }

    public AccountRegister accountIndividuRegister(AccountIndividuRegister accountIndividuRegister) {
        this.accountIndividuRegister = accountIndividuRegister;
        return this;
    }

    public void setAccountIndividuRegister(AccountIndividuRegister accountIndividuRegister) {
        this.accountIndividuRegister = accountIndividuRegister;
    }

    public AccountInstitutionRegister getAccountInstitutionRegister() {
        return accountInstitutionRegister;
    }

    public AccountRegister accountInstitutionRegister(AccountInstitutionRegister accountInstitutionRegister) {
        this.accountInstitutionRegister = accountInstitutionRegister;
        return this;
    }

    public void setAccountInstitutionRegister(AccountInstitutionRegister accountInstitutionRegister) {
        this.accountInstitutionRegister = accountInstitutionRegister;
    }

    public Set<AccountAuthorizeRegister> getAccountAuthorizeRegisters() {
        return accountAuthorizeRegisters;
    }

    public AccountRegister accountAuthorizeRegisters(Set<AccountAuthorizeRegister> accountAuthorizeRegisters) {
        this.accountAuthorizeRegisters = accountAuthorizeRegisters;
        return this;
    }

    public AccountRegister addAccountAuthorizeRegister(AccountAuthorizeRegister accountAuthorizeRegister) {
        this.accountAuthorizeRegisters.add(accountAuthorizeRegister);
        accountAuthorizeRegister.setAccountRegister(this);
        return this;
    }

    public AccountRegister removeAccountAuthorizeRegister(AccountAuthorizeRegister accountAuthorizeRegister) {
        this.accountAuthorizeRegisters.remove(accountAuthorizeRegister);
        accountAuthorizeRegister.setAccountRegister(null);
        return this;
    }

    public void setAccountAuthorizeRegisters(Set<AccountAuthorizeRegister> accountAuthorizeRegisters) {
        this.accountAuthorizeRegisters = accountAuthorizeRegisters;
    }

    public Set<InvestorAddressRegister> getInvestorAddressRegisters() {
        return investorAddressRegisters;
    }

    public AccountRegister investorAddressRegisters(Set<InvestorAddressRegister> investorAddressRegisters) {
        this.investorAddressRegisters = investorAddressRegisters;
        return this;
    }

    public AccountRegister addInvestorAddressRegister(InvestorAddressRegister investorAddressRegister) {
        this.investorAddressRegisters.add(investorAddressRegister);
        investorAddressRegister.setAccountRegister(this);
        return this;
    }

    public AccountRegister removeInvestorAddressRegister(InvestorAddressRegister investorAddressRegister) {
        this.investorAddressRegisters.remove(investorAddressRegister);
        investorAddressRegister.setAccountRegister(null);
        return this;
    }

    public void setInvestorAddressRegisters(Set<InvestorAddressRegister> investorAddressRegisters) {
        this.investorAddressRegisters = investorAddressRegisters;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AccountRegister)) {
            return false;
        }
        return id != null && id.equals(((AccountRegister) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AccountRegister{" +
            "id=" + getId() +
            ", registerCode='" + getRegisterCode() + "'" +
            ", accountName='" + getAccountName() + "'" +
            ", accountType='" + getAccountType() + "'" +
            ", accountAngle='" + isAccountAngle() + "'" +
            ", kseiClientCode='" + getKseiClientCode() + "'" +
            ", kseiSubrek='" + getKseiSubrek() + "'" +
            ", createSystemDate='" + getCreateSystemDate() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUserId=" + getCreateUserId() +
            ", lastModificationSystemDate='" + getLastModificationSystemDate() + "'" +
            ", lastModificationDate='" + getLastModificationDate() + "'" +
            ", lastModificationUserId=" + getLastModificationUserId() +
            "}";
    }
}
