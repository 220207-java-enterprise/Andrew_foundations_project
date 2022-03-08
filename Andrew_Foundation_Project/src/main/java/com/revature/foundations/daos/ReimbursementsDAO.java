package com.revature.foundations.daos;

import com.revature.foundations.models.ERSRembursements;
import com.revature.foundations.models.ERSReimbursementsStatuses;
import com.revature.foundations.models.ERSReimbursementTypes;

import com.revature.foundations.util.ConnectionFactory;
import com.revature.foundations.util.exceptions.DataSourceException;
import com.revature.foundations.util.exceptions.ResourcePersistenceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementsDAO implements CrudDAO<ERSRembursements>{

    private final String rootSelect = "SELECT er.REIMB_ID, " +
            "er.AMOUNT, " +
            "er.SUBMITTED, " +
            "er.RESOLVED, " +
            "er.DESCRIPTION, " +
            "er.PAYMENT_ID, " +
            "er.AUTHOR_ID, " +
            "er.RESOLVER_ID, " +
            "er.STATUS_ID " +
            "er.TYPE_ID " +
            "ers.STATUS " +
            "ert.TYPEOF " +
            "FROM ers_reimbursements er " +
            "JOIN ers_reimbursements_statuses ers " +
            "ON er.STATUS_ID = ers.STATUS_ID " +
            "JOIN ers_reimbursements_types ert " +
            "ON er.TYPE_ID = ert.TYPE_ID ";

    @Override
    public void save(ERSRembursements newReimbursement) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ers_reimbursements VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, newReimbursement.getReimbId());
            pstmt.setDouble(2, newReimbursement.getAmount());
            pstmt.setTimestamp(3, newReimbursement.getSubmitted());
            pstmt.setTimestamp(4, newReimbursement.getResolved());
            pstmt.setString(5, newReimbursement.getReceipt());//TODO setBytea
            pstmt.setString(6, newReimbursement.getPaymentId());
            pstmt.setString(7, newReimbursement.getAuthorId());
            pstmt.setString(8, newReimbursement.getResolverId());
            pstmt.setString(9, newReimbursement.getStatusId().getStatusId());
            pstmt.setString(10, newReimbursement.getTypeId().getTypeId());

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted != 1) {
                conn.rollback();
                throw new ResourcePersistenceException("Failed to persist user to data source");
            }

            conn.commit();

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
    }

    @Override
    public ERSRembursements getById(String id) {
        return null;
    }

    @Override
    public List<ERSRembursements> getAll() {
        return null;
    }

    @Override
    public void update(ERSRembursements updatedObject) {

    }
}
