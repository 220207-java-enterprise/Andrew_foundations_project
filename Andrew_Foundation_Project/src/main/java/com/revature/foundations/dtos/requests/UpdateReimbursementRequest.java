package com.revature.foundations.dtos.requests;

import com.revature.foundations.daos.ReimbursementsDAO;
import com.revature.foundations.models.*;

import java.sql.Timestamp;
import java.util.UUID;

public class UpdateReimbursementRequest {

    private String reimbId;
    private String resolverId;
    private String status;

    public UpdateReimbursementRequest(){
        super();
    }

    public UpdateReimbursementRequest(String reimbId, String resolverId, String status) {
        this.reimbId = reimbId;
        this.resolverId = resolverId;
        this.status = status;
    }

    public String getReimbId() {
        return reimbId;
    }

    public void setReimbId(String reimbId) {
        this.reimbId = reimbId;
    }

    public String getResolverId() {
        return resolverId;
    }

    public void setResolverId(String resolverId) {
        this.resolverId = resolverId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
