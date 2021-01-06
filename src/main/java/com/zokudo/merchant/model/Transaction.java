package com.zokudo.merchant.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@TableGenerator(name="transaction", table="SEQUENCES", pkColumnValue="Transaction",allocationSize=1)
public class Transaction {
    @Id
    @GeneratedValue(strategy= GenerationType.TABLE, generator="transaction")
    @Column(name="id")
    private int id;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    @Column(name = "merchant_name")
    private String merchantName;

    @Column(name = "merchant_id")
    private String merchantId;

    @Column(name = "program_id")
    private String programId;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "customer_hash_id")
    private String customerHashId;

    @Column(name = "wallet_hash_id")
    private String walletWashId;

    @Column(name = "mid")
    private String mid;

    @Column(name="amount")
    private Float amount;

    @Column(name="proxy_card_no")
    private String proxyCardNo;

    @Column(name="transaction_ref_no")
    private String transactionRefNo;

    @Column(name="retrieval_ref_no")
    private String retrievalRefNo;

    @Column(name="transaction_type")
    private String transactionType;

    @Column(name="comments")
    private String comments;

    @Column(name="transaction_amount")
    private Float transactionAmount;

    @Column(name="billing_amount")
    private Float billingBmount;

    @Column(name="cashback_amount")
    private Float cashbackBmount;

    @Column(name="current_balance")
    private Float currentBalance;

    @Column(name="status")
    private String  status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getCustomerHashId() {
        return customerHashId;
    }

    public void setCustomerHashId(String customerHashId) {
        this.customerHashId = customerHashId;
    }

    public String getWalletWashId() {
        return walletWashId;
    }

    public void setWalletWashId(String walletWashId) {
        this.walletWashId = walletWashId;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getProxyCardNo() {
        return proxyCardNo;
    }

    public void setProxyCardNo(String proxyCardNo) {
        this.proxyCardNo = proxyCardNo;
    }

    public String getTransactionRefNo() {
        return transactionRefNo;
    }

    public void setTransactionRefNo(String transactionRefNo) {
        this.transactionRefNo = transactionRefNo;
    }

    public String getRetrievalRefNo() {
        return retrievalRefNo;
    }

    public void setRetrievalRefNo(String retrievalRefNo) {
        this.retrievalRefNo = retrievalRefNo;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Float getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Float transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Float getBillingBmount() {
        return billingBmount;
    }

    public void setBillingBmount(Float billingBmount) {
        this.billingBmount = billingBmount;
    }

    public Float getCashbackBmount() {
        return cashbackBmount;
    }

    public void setCashbackBmount(Float cashbackBmount) {
        this.cashbackBmount = cashbackBmount;
    }

    public Float getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Float currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
/*public enum status {
        active ("active"), //  Plan is active and there are some upcoming transactions
        cancelled("cancelled"), //  Plan is cancelled by business
        paused("paused"),  // Plan has been paused by business
        completed("completed"),//   All transactions have been created for this plan
        Approved("Approved"),
        Declined("Declined"),
        scheduled("scheduled"),  // Transaction is scheduled
        skipped("skipped") , // Transaction is skipped ( when plan is in paused state )
        processing("processing"),   // Transaction is under process
        delayed ("delayed"),   // Transaction response is delayed from NPCI side
        failed ("failed"),   // Transaction failed
        authenticated ("authenticated"),   // Transaction successful from customer but settlement pending(applicable only for alternate payments like UPI, Payment Link)
        payment_success("payment_success"), //Transaction success
        payment_failed("payment_failed"), //Transaction failed
        success ("success"),   // Transaction has been completed successfully
        accepted_spo_bank("accepted_spo_bank"), //Transaction accepted by SPO bank
        reject_spo_bank("reject_spo_bank"), //Transaction rejected by SPO bank
        created_new("new"); //New transaction created

        private final String statusName;

        private status(String s) {
            statusName = s;
        }

        public boolean equalsName(String otherName) {
            // (otherName == null) check is not needed because name.equals(null) returns false
            return statusName.equals(otherName);
        }

        public String toString() {
            return this.statusName;
        }
    }

    public Transaction.status getStatus() {
        return status;
    }

    public void setStatus(Transaction.status status) {
        this.status = status;
    }*/


}
