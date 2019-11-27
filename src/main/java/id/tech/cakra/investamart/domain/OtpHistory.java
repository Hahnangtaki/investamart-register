package id.tech.cakra.investamart.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;

/**
 * A OtpHistory.
 */
@Entity
@Table(name = "otp_history")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OtpHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "otp_created_date")
    private ZonedDateTime otpCreatedDate;

    @Column(name = "otp_expired_date")
    private ZonedDateTime otpExpiredDate;

    @Column(name = "member_email")
    private String memberEmail;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "proc_type")
    private Integer procType;

    @Column(name = "token_text")
    private String tokenText;

    @Column(name = "token")
    private String token;

    @Column(name = "retry_count")
    private Integer retryCount;

    @Column(name = "retry_max")
    private Integer retryMax;

    @Lob
    @Column(name = "request_data")
    private byte[] requestData;

    @Column(name = "request_data_content_type")
    private String requestDataContentType;

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
    @JsonIgnoreProperties("otpHistories")
    private Visitor visitor;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public OtpHistory createdBy(Integer createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getOtpCreatedDate() {
        return otpCreatedDate;
    }

    public OtpHistory otpCreatedDate(ZonedDateTime otpCreatedDate) {
        this.otpCreatedDate = otpCreatedDate;
        return this;
    }

    public void setOtpCreatedDate(ZonedDateTime otpCreatedDate) {
        this.otpCreatedDate = otpCreatedDate;
    }

    public ZonedDateTime getOtpExpiredDate() {
        return otpExpiredDate;
    }

    public OtpHistory otpExpiredDate(ZonedDateTime otpExpiredDate) {
        this.otpExpiredDate = otpExpiredDate;
        return this;
    }

    public void setOtpExpiredDate(ZonedDateTime otpExpiredDate) {
        this.otpExpiredDate = otpExpiredDate;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public OtpHistory memberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
        return this;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public OtpHistory phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getProcType() {
        return procType;
    }

    public OtpHistory procType(Integer procType) {
        this.procType = procType;
        return this;
    }

    public void setProcType(Integer procType) {
        this.procType = procType;
    }

    public String getTokenText() {
        return tokenText;
    }

    public OtpHistory tokenText(String tokenText) {
        this.tokenText = tokenText;
        return this;
    }

    public void setTokenText(String tokenText) {
        this.tokenText = tokenText;
    }

    public String getToken() {
        return token;
    }

    public OtpHistory token(String token) {
        this.token = token;
        return this;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getRetryCount() {
        return retryCount;
    }

    public OtpHistory retryCount(Integer retryCount) {
        this.retryCount = retryCount;
        return this;
    }

    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }

    public Integer getRetryMax() {
        return retryMax;
    }

    public OtpHistory retryMax(Integer retryMax) {
        this.retryMax = retryMax;
        return this;
    }

    public void setRetryMax(Integer retryMax) {
        this.retryMax = retryMax;
    }

    public byte[] getRequestData() {
        return requestData;
    }

    public OtpHistory requestData(byte[] requestData) {
        this.requestData = requestData;
        return this;
    }

    public void setRequestData(byte[] requestData) {
        this.requestData = requestData;
    }

    public String getRequestDataContentType() {
        return requestDataContentType;
    }

    public OtpHistory requestDataContentType(String requestDataContentType) {
        this.requestDataContentType = requestDataContentType;
        return this;
    }

    public void setRequestDataContentType(String requestDataContentType) {
        this.requestDataContentType = requestDataContentType;
    }

    public LocalDate getCreateSystemDate() {
        return createSystemDate;
    }

    public OtpHistory createSystemDate(LocalDate createSystemDate) {
        this.createSystemDate = createSystemDate;
        return this;
    }

    public void setCreateSystemDate(LocalDate createSystemDate) {
        this.createSystemDate = createSystemDate;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public OtpHistory createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public OtpHistory createUserId(Long createUserId) {
        this.createUserId = createUserId;
        return this;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public LocalDate getLastModificationSystemDate() {
        return lastModificationSystemDate;
    }

    public OtpHistory lastModificationSystemDate(LocalDate lastModificationSystemDate) {
        this.lastModificationSystemDate = lastModificationSystemDate;
        return this;
    }

    public void setLastModificationSystemDate(LocalDate lastModificationSystemDate) {
        this.lastModificationSystemDate = lastModificationSystemDate;
    }

    public ZonedDateTime getLastModificationDate() {
        return lastModificationDate;
    }

    public OtpHistory lastModificationDate(ZonedDateTime lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
        return this;
    }

    public void setLastModificationDate(ZonedDateTime lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public Long getLastModificationUserId() {
        return lastModificationUserId;
    }

    public OtpHistory lastModificationUserId(Long lastModificationUserId) {
        this.lastModificationUserId = lastModificationUserId;
        return this;
    }

    public void setLastModificationUserId(Long lastModificationUserId) {
        this.lastModificationUserId = lastModificationUserId;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public OtpHistory visitor(Visitor visitor) {
        this.visitor = visitor;
        return this;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OtpHistory)) {
            return false;
        }
        return id != null && id.equals(((OtpHistory) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "OtpHistory{" +
            "id=" + getId() +
            ", createdBy=" + getCreatedBy() +
            ", otpCreatedDate='" + getOtpCreatedDate() + "'" +
            ", otpExpiredDate='" + getOtpExpiredDate() + "'" +
            ", memberEmail='" + getMemberEmail() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", procType=" + getProcType() +
            ", tokenText='" + getTokenText() + "'" +
            ", token='" + getToken() + "'" +
            ", retryCount=" + getRetryCount() +
            ", retryMax=" + getRetryMax() +
            ", requestData='" + getRequestData() + "'" +
            ", requestDataContentType='" + getRequestDataContentType() + "'" +
            ", createSystemDate='" + getCreateSystemDate() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUserId=" + getCreateUserId() +
            ", lastModificationSystemDate='" + getLastModificationSystemDate() + "'" +
            ", lastModificationDate='" + getLastModificationDate() + "'" +
            ", lastModificationUserId=" + getLastModificationUserId() +
            "}";
    }
}
