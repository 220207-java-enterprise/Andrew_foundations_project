package com.revature.foundations.dtos.requests;

import com.revature.foundations.models.ERSReimbursementTypes;
import com.revature.foundations.models.ERSReimbursements;
import com.revature.foundations.models.ERSReimbursementsStatuses;

import java.sql.Timestamp;
import java.util.UUID;

public class ReimbursementRequest {

    private Double amount; //TODO set max decimales to 2
    private String description; //TODO insert image
    private String authorId;
    private String typeId;

    public ReimbursementRequest(){
        super();
    }

    public ReimbursementRequest(Double amount, String description, String authorId, String typeId) {
        this.amount = amount;
        this.description = description;
        this.authorId = authorId;
        this.typeId = typeId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public ERSReimbursements extractReimbursement() {
        String reimbId = UUID.randomUUID().toString();
        ERSReimbursementsStatuses status = new ERSReimbursementsStatuses("PENDING", "PENDING");
        ERSReimbursementTypes type = new ERSReimbursementTypes(this.typeId, this.typeId);
        return new ERSReimbursements(reimbId, this.amount, new Timestamp(System.currentTimeMillis()),
                new Timestamp(0), this.description, null, this.authorId, null, status, type);
    }
    @Override
    public String toString() {
        return "ReimbursementRequest{" +
                "amount=" + amount +
                ", description='" + description + '\'' +
                ", authorId='" + authorId + '\'' +
                ", typeId='" + typeId + '\'' +
                '}';
    }
}
