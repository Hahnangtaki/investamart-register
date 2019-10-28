package id.tech.cakra.investamart.service.dto;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link id.tech.cakra.investamart.domain.AccountRegister} entity.
 */
public class AccountRegisterDTO implements Serializable {

    private Long id;

    @Size(max = 10)
    private String registerCode;

    @Size(max = 10)
    private String accountName;

    @Size(max = 1)
    private String accountType;

    private Boolean accountAngle;

    @Size(max = 4)
    private String kseiClientCode;

    @Size(max = 15)
    private String kseiSubrek;

    private LocalDate createSystemDate;

    private ZonedDateTime createDate;

    private Long createUserId;

    private LocalDate lastModificationSystemDate;

    private ZonedDateTime lastModificationDate;

    private Long lastModificationUserId;


    private Long accountIndividuRegisterId;

    private Long accountInstitutionRegisterId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegisterCode() {
        return registerCode;
    }

    public void setRegisterCode(String registerCode) {
        this.registerCode = registerCode;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Boolean isAccountAngle() {
        return accountAngle;
    }

    public void setAccountAngle(Boolean accountAngle) {
        this.accountAngle = accountAngle;
    }

    public String getKseiClientCode() {
        return kseiClientCode;
    }

    public void setKseiClientCode(String kseiClientCode) {
        this.kseiClientCode = kseiClientCode;
    }

    public String getKseiSubrek() {
        return kseiSubrek;
    }

    public void setKseiSubrek(String kseiSubrek) {
        this.kseiSubrek = kseiSubrek;
    }

    public LocalDate getCreateSystemDate() {
        return createSystemDate;
    }

    public void setCreateSystemDate(LocalDate createSystemDate) {
        this.createSystemDate = createSystemDate;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public LocalDate getLastModificationSystemDate() {
        return lastModificationSystemDate;
    }

    public void setLastModificationSystemDate(LocalDate lastModificationSystemDate) {
        this.lastModificationSystemDate = lastModificationSystemDate;
    }

    public ZonedDateTime getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(ZonedDateTime lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public Long getLastModificationUserId() {
        return lastModificationUserId;
    }

    public void setLastModificationUserId(Long lastModificationUserId) {
        this.lastModificationUserId = lastModificationUserId;
    }

    public Long getAccountIndividuRegisterId() {
        return accountIndividuRegisterId;
    }

    public void setAccountIndividuRegisterId(Long accountIndividuRegisterId) {
        this.accountIndividuRegisterId = accountIndividuRegisterId;
    }

    public Long getAccountInstitutionRegisterId() {
        return accountInstitutionRegisterId;
    }

    public void setAccountInstitutionRegisterId(Long accountInstitutionRegisterId) {
        this.accountInstitutionRegisterId = accountInstitutionRegisterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AccountRegisterDTO accountRegisterDTO = (AccountRegisterDTO) o;
        if (accountRegisterDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), accountRegisterDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AccountRegisterDTO{" +
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
            ", accountIndividuRegister=" + getAccountIndividuRegisterId() +
            ", accountInstitutionRegister=" + getAccountInstitutionRegisterId() +
            "}";
    }
}
