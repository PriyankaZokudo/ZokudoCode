package com.zokudo.merchant.model;

public class MerchantTransactionModel {
    private String mId;
    private int transactionId;
    private String createdAt;
    private String updatedAt;
    private String merchantName;
    private String merchantId;
    private String programId;
    private String clientId;
    private String customerHashId;
    private String walletWashId;
    private String mid;
    private Float amount;
    private String proxyCardNo;
    private String transactionRefNo;
    private String retrievalRefNo;
    private String transactionType;
    private String comments;
    private Float transactionAmount;
    private Float billingBmount;
    private Float cashbackBmount;
    private Float currentBalance;
    private String  status;

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
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

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
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
}
