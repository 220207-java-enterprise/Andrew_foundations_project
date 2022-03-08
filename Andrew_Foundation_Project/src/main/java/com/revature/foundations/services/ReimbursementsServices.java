package com.revature.foundations.services;

import com.revature.foundations.daos.ReimbursementsDAO;
import com.revature.foundations.dtos.requests.ReimbursementRequest;
import com.revature.foundations.dtos.requests.UpdateReimbursementRequest;
import com.revature.foundations.dtos.responses.ReimbursementResponse;
import com.revature.foundations.models.ERSReimbursements;
import com.revature.foundations.models.ERSReimbursementsStatuses;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public class ReimbursementsServices {

    private ReimbursementsDAO reimbursementsDAO;

    public ReimbursementsServices(ReimbursementsDAO reimbursementsDAO) {
        this.reimbursementsDAO = reimbursementsDAO;
    }

    public List<ReimbursementResponse> getAllReimbursements() {
        return reimbursementsDAO.getAll()
                .stream()
                .map(ReimbursementResponse::new)
                .collect(Collectors.toList());
    }

    public ERSReimbursements NewReimbursement(ReimbursementRequest reimbursementRequest) throws IOException {

        ERSReimbursements newReimbursement = reimbursementRequest.extractReimbursement();
        reimbursementsDAO.save(newReimbursement);
        return newReimbursement;
    }

    public ERSReimbursements updateReimbursementStatus(UpdateReimbursementRequest updateReimbursementRequest){
        ERSReimbursements reimbursement = reimbursementsDAO.getById(updateReimbursementRequest.getReimbId());
        ERSReimbursementsStatuses reimbursementsStatuses = new ERSReimbursementsStatuses(
                updateReimbursementRequest.getStatus(), updateReimbursementRequest.getStatus());

        reimbursement.setStatusId(reimbursementsStatuses);
        reimbursement.setResolved(new Timestamp(System.currentTimeMillis()));
        reimbursement.setResolverId(updateReimbursementRequest.getResolverId());

        reimbursementsDAO.update(reimbursement);

        return reimbursementsDAO.getById(updateReimbursementRequest.getReimbId());
    }



}
