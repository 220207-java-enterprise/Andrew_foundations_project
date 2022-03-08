package com.revature.foundations.models;

import java.sql.Timestamp;
import java.util.Objects;

public class ERSReimbursements {
    private String reimbId;
    private Double amount; //TODO set max decimales to 2
    private Timestamp submitted;
    private Timestamp resolved;
    private String description; //TODO insert image
    private String paymentId;
    private String authorId;
    private String resolverId;
    private ERSReimbursementsStatuses statusId;
    private ERSReimbursementTypes typeId;

    public ERSReimbursements(){
        super();
    }

    public ERSReimbursements(String reimbId, Double amount, Timestamp submitted, Timestamp resolved, String description,
                             String paymentId, String authorId, String resolverId, ERSReimbursementsStatuses statusId,
                             ERSReimbursementTypes typeId) {
        this.reimbId = reimbId;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
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

    public Timestamp getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Timestamp submitted) {
        this.submitted = submitted;
    }

    public Timestamp getResolved() {
        return resolved;
    }

    public void setResolved(Timestamp resolved) {
        this.resolved = resolved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public ERSReimbursementsStatuses getStatusId() {
        return statusId;
    }

    public void setStatusId(ERSReimbursementsStatuses statusId) {
        this.statusId = statusId;
    }

    public ERSReimbursementTypes getTypeId() {
        return typeId;
    }

    public void setTypeId(ERSReimbursementTypes typeId) {
        this.typeId = typeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ERSReimbursements that = (ERSReimbursements) o;
        return Objects.equals(reimbId, that.reimbId)
                && Objects.equals(amount, that.amount)
                && Objects.equals(submitted, that.submitted)
                && Objects.equals(resolved, that.resolved)
                && Objects.equals(description, that.description)
                && Objects.equals(paymentId, that.paymentId)
                && Objects.equals(authorId, that.authorId)
                && Objects.equals(resolverId, that.resolverId)
                && Objects.equals(statusId, that.statusId)
                && Objects.equals(typeId, that.typeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reimbId, amount, submitted, resolved, description, paymentId, authorId,
                resolverId, statusId, typeId);
    }

    @Override
    public String toString() {
        return "ERSRembursements{" +
                "reimbId='" + reimbId + '\'' +
                ", amount=" + amount +
                ", submitted='" + submitted + '\'' +
                ", resolved='" + resolved + '\'' +
                ", receipt='" + description + '\'' +
                ", paymentId='" + paymentId + '\'' +
                ", authorId='" + authorId + '\'' +
                ", resolverId='" + resolverId + '\'' +
                ", statusId='" + statusId + '\'' +
                ", typeId='" + typeId + '\'' +
                '}';
    }

}
