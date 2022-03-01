package com.revature.foundations.models;

import java.util.Objects;

public class ERSRembursements {
    private String reimbId;
    private Double amount; //TODO set max decimales to 2
    private String submitted; //TODO set timeStamp
    private String resolved;
    private String receipt; //TODO insert image
    private String paymentId;
    private String authorId;
    private String resolverId;
    private String statusId;
    private String typeId;

    public ERSRembursements(){
        super();
    }

    public ERSRembursements(String reimbId, Double amount, String submitted, String resolved, String receipt,
                            String paymentId, String authorId, String resolverId, String statusId, String typeId) {
        this.reimbId = reimbId;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.receipt = receipt;
        this.paymentId = paymentId;
        this.authorId = authorId;
        this.resolverId = resolverId;
        this.statusId = statusId;
        this.typeId = typeId;
    }

    public String getReimbId() {
        return reimbId;
    }

    public void setReimbId(String reimbId) {
        this.reimbId = reimbId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getSubmitted() {
        return submitted;
    }

    public void setSubmitted(String submitted) {
        this.submitted = submitted;
    }

    public String getResolved() {
        return resolved;
    }

    public void setResolved(String resolved) {
        this.resolved = resolved;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getResolverId() {
        return resolverId;
    }

    public void setResolverId(String resolverId) {
        this.resolverId = resolverId;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ERSRembursements that = (ERSRembursements) o;
        return Objects.equals(reimbId, that.reimbId)
                && Objects.equals(amount, that.amount)
                && Objects.equals(submitted, that.submitted)
                && Objects.equals(resolved, that.resolved)
                && Objects.equals(receipt, that.receipt)
                && Objects.equals(paymentId, that.paymentId)
                && Objects.equals(authorId, that.authorId)
                && Objects.equals(resolverId, that.resolverId)
                && Objects.equals(statusId, that.statusId)
                && Objects.equals(typeId, that.typeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reimbId, amount, submitted, resolved, receipt, paymentId, authorId,
                resolverId, statusId, typeId);
    }

    @Override
    public String toString() {
        return "ERSRembursements{" +
                "reimbId='" + reimbId + '\'' +
                ", amount=" + amount +
                ", submitted='" + submitted + '\'' +
                ", resolved='" + resolved + '\'' +
                ", receipt='" + receipt + '\'' +
                ", paymentId='" + paymentId + '\'' +
                ", authorId='" + authorId + '\'' +
                ", resolverId='" + resolverId + '\'' +
                ", statusId='" + statusId + '\'' +
                ", typeId='" + typeId + '\'' +
                '}';
    }

}
