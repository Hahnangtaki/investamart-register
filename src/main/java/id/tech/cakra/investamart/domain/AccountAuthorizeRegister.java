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
 * A AccountAuthorizeRegister.
 */
@Entity
@Table(name = "account_authorize_register")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AccountAuthorizeRegister implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 35)
    @Column(name = "first_name", length = 35)
    private String firstName;

    @Size(max = 35)
    @Column(name = "middle_name", length = 35)
    private String middleName;

    @Size(max = 35)
    @Column(name = "last_name", length = 35)
    private String lastName;

    @Size(max = 100)
    @Column(name = "position", length = 100)
    private String position;

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
    @JsonIgnoreProperties("accountAuthorizeRegisters")
    private AccountRegister accountRegister;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public AccountAuthorizeRegister firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public AccountAuthorizeRegister middleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public AccountAuthorizeRegister lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public AccountAuthorizeRegister position(String position) {
        this.position = position;
        return this;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getKtp() {
        return ktp;
    }

    public AccountAuthorizeRegister ktp(String ktp) {
        this.ktp = ktp;
        return this;
    }

    public void setKtp(String ktp) {
        this.ktp = ktp;
    }

    public LocalDate getKtpExpDate() {
        return ktpExpDate;
    }

    public AccountAuthorizeRegister ktpExpDate(LocalDate ktpExpDate) {
        this.ktpExpDate = ktpExpDate;
        return this;
    }

    public void setKtpExpDate(LocalDate ktpExpDate) {
        this.ktpExpDate = ktpExpDate;
    }

    public String getNpwp() {
        return npwp;
    }

    public AccountAuthorizeRegister npwp(String npwp) {
        this.npwp = npwp;
        return this;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    public LocalDate getNpwpRegDate() {
        return npwpRegDate;
    }

    public AccountAuthorizeRegister npwpRegDate(LocalDate npwpRegDate) {
        this.npwpRegDate = npwpRegDate;
        return this;
    }

    public void setNpwpRegDate(LocalDate npwpRegDate) {
        this.npwpRegDate = npwpRegDate;
    }

    public String getPassport() {
        return passport;
    }

    public AccountAuthorizeRegister passport(String passport) {
        this.passport = passport;
        return this;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public LocalDate getPassportExpDate() {
        return passportExpDate;
    }

    public AccountAuthorizeRegister passportExpDate(LocalDate passportExpDate) {
        this.passportExpDate = passportExpDate;
        return this;
    }

    public void setPassportExpDate(LocalDate passportExpDate) {
        this.passportExpDate = passportExpDate;
    }

    public String getKitas() {
        return kitas;
    }

    public AccountAuthorizeRegister kitas(String kitas) {
        this.kitas = kitas;
        return this;
    }

    public void setKitas(String kitas) {
        this.kitas = kitas;
    }

    public LocalDate getKitasExpDate() {
        return kitasExpDate;
    }

    public AccountAuthorizeRegister kitasExpDate(LocalDate kitasExpDate) {
        this.kitasExpDate = kitasExpDate;
        return this;
    }

    public void setKitasExpDate(LocalDate kitasExpDate) {
        this.kitasExpDate = kitasExpDate;
    }

    public LocalDate getCreateSystemDate() {
        return createSystemDate;
    }

    public AccountAuthorizeRegister createSystemDate(LocalDate createSystemDate) {
        this.createSystemDate = createSystemDate;
        return this;
    }

    public void setCreateSystemDate(LocalDate createSystemDate) {
        this.createSystemDate = createSystemDate;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public AccountAuthorizeRegister createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public AccountAuthorizeRegister createUserId(Long createUserId) {
        this.createUserId = createUserId;
        return this;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public LocalDate getLastModificationSystemDate() {
        return lastModificationSystemDate;
    }

    public AccountAuthorizeRegister lastModificationSystemDate(LocalDate lastModificationSystemDate) {
        this.lastModificationSystemDate = lastModificationSystemDate;
        return this;
    }

    public void setLastModificationSystemDate(LocalDate lastModificationSystemDate) {
        this.lastModificationSystemDate = lastModificationSystemDate;
    }

    public ZonedDateTime getLastModificationDate() {
        return lastModificationDate;
    }

    public AccountAuthorizeRegister lastModificationDate(ZonedDateTime lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
        return this;
    }

    public void setLastModificationDate(ZonedDateTime lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public Long getLastModificationUserId() {
        return lastModificationUserId;
    }

    public AccountAuthorizeRegister lastModificationUserId(Long lastModificationUserId) {
        this.lastModificationUserId = lastModificationUserId;
        return this;
    }

    public void setLastModificationUserId(Long lastModificationUserId) {
        this.lastModificationUserId = lastModificationUserId;
    }

    public AccountRegister getAccountRegister() {
        return accountRegister;
    }

    public AccountAuthorizeRegister accountRegister(AccountRegister accountRegister) {
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
        if (!(o instanceof AccountAuthorizeRegister)) {
            return false;
        }
        return id != null && id.equals(((AccountAuthorizeRegister) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AccountAuthorizeRegister{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", middleName='" + getMiddleName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", position='" + getPosition() + "'" +
            ", ktp='" + getKtp() + "'" +
            ", ktpExpDate='" + getKtpExpDate() + "'" +
            ", npwp='" + getNpwp() + "'" +
            ", npwpRegDate='" + getNpwpRegDate() + "'" +
            ", passport='" + getPassport() + "'" +
            ", passportExpDate='" + getPassportExpDate() + "'" +
            ", kitas='" + getKitas() + "'" +
            ", kitasExpDate='" + getKitasExpDate() + "'" +
            ", createSystemDate='" + getCreateSystemDate() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUserId=" + getCreateUserId() +
            ", lastModificationSystemDate='" + getLastModificationSystemDate() + "'" +
            ", lastModificationDate='" + getLastModificationDate() + "'" +
            ", lastModificationUserId=" + getLastModificationUserId() +
            "}";
    }
}
