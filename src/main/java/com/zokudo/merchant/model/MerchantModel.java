package com.zokudo.merchant.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "merchant")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class MerchantModel {
    @Id
    @GeneratedValue
    @Column(name="id")
    private long id;

    @NotNull
    @Column(name="created_at")
    private LocalDate createdAt;

    @NotNull
    @Column(name="created_by")
    private String createdBy;

    @NotNull
    @Column(name="updated_at")
    private LocalDate updatedAt;

    @Column(name="updated_by")
    private String updatedBy;

    @Column(name="email")
    private String email;

    @Column(name="merchant_hash_id")
    private String merchantHashId;

    @Column(name="m_id")
    private String mId;

    @Column(name="mobile_no")
    private long mobileNo;

    @Column(name="t_id", nullable = true)
    private Long tId;

    @Column(name="merchant_full_name")
    private String merchantFullName;

    @Column(name="terminal_full_name")
    private String terminalFullName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMerchantHashId() {
        return merchantHashId;
    }

    public void setMerchantHashId(String merchantHashId) {
        this.merchantHashId = merchantHashId;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(long mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Long gettId() {
        return tId;
    }

    public void settId(Long tId) {
        this.tId = tId;
    }

    public String getMerchantFullName() {
        return merchantFullName;
    }

    public void setMerchantFullName(String merchantFullName) {
        this.merchantFullName = merchantFullName;
    }

    public String getTerminalFullName() {
        return terminalFullName;
    }

    public void setTerminalFullName(String terminalFullName) {
        this.terminalFullName = terminalFullName;
    }


}
