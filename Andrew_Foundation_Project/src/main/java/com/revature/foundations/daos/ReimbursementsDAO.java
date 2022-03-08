package com.revature.foundations.daos;

import com.revature.foundations.models.*;

import com.revature.foundations.util.ConnectionFactory;
import com.revature.foundations.util.exceptions.DataSourceException;
import com.revature.foundations.util.exceptions.ResourcePersistenceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementsDAO implements CrudDAO<ERSReimbursements>{

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
    public void save(ERSReimbursements newReimbursement) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ers_reimbursements VALUES (?, ?, ?, ?," +
                    " ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, newReimbursement.getReimbId());
            pstmt.setDouble(2, newReimbursement.getAmount());
            pstmt.setTimestamp(3, newReimbursement.getSubmitted());
            pstmt.setTimestamp(4, newReimbursement.getResolved());
            pstmt.setString(5, newReimbursement.getDescription());//TODO setBytea
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
    public ERSReimbursements getById(String id) {

        ERSReimbursements Reimbursements = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE reimb_id = ?");
            pstmt.setString(1, id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Reimbursements = new ERSReimbursements();
                Reimbursements.setReimbId(rs.getString("reimbId"));
                Reimbursements.setAmount(rs.getDouble("amount"));
                Reimbursements.setSubmitted(rs.getTimestamp("submitted"));
                Reimbursements.setResolved(rs.getTimestamp("resolved"));
                Reimbursements.setDescription(rs.getString("description"));//TODO set Bytea
                Reimbursements.setPaymentId(rs.getString("paymentId"));
                Reimbursements.setAuthorId(rs.getString("authorId"));
                Reimbursements.setResolverId(rs.getString("resolverId"));
                Reimbursements.setStatusId(new ERSReimbursementsStatuses(rs.getString("statusesId"),
                        rs.getString("status")));
                Reimbursements.setTypeId(new ERSReimbursementTypes(rs.getString("typeId"),
                        rs.getString("typeOf")));

            }

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }

        return Reimbursements;

    }

    @Override
    public List<ERSReimbursements> getAll() {
        List<ERSReimbursements> Reimbursements = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            ResultSet rs = conn.createStatement().executeQuery(rootSelect);
            while (rs.next()) {
                ERSReimbursements Reimbursement = new ERSReimbursements();
                Reimbursement.setReimbId(rs.getString("reimbId"));
                Reimbursement.setAmount(rs.getDouble("amount"));
                Reimbursement.setSubmitted(rs.getTimestamp("submitted"));
                Reimbursement.setResolved(rs.getTimestamp("resolved"));
                Reimbursement.setDescription(rs.getString("description"));//TODO set Bytea
                Reimbursement.setPaymentId(rs.getString("paymentId"));
                Reimbursement.setAuthorId(rs.getString("authorId"));
                Reimbursement.setResolverId(rs.getString("resolverId"));
                Reimbursement.setStatusId(new ERSReimbursementsStatuses(rs.getString("statusesId"),
                        rs.getString("status")));
                Reimbursement.setTypeId(new ERSReimbursementTypes(rs.getString("typeId"),
                        rs.getString("typeOf")));
                Reimbursements.add(Reimbursement);
            }
        } catch (SQLException e) {
            throw new DataSourceException(e);
        }

        return Reimbursements;
    }

    @Override
    public void update(ERSReimbursements updateReimbursements) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement("UPDATE ers_reimbursements " +
                    "SET amount = ?, " +
                    "resolved = ?, " +
                    "description = ?, " +
                    "payment_id = ?, " +
                    "author_id = ?, " +
                    "resolver_id = ?, " +
                    "status_id = ? " +
                    "type_id = ? " +
                    "WHERE reimb_id = ?");

            pstmt.setDouble(1, updateReimbursements.getAmount());
            pstmt.setTimestamp(2, updateReimbursements.getResolved());
            pstmt.setString(3, updateReimbursements.getDescription());
            pstmt.setString(4, updateReimbursements.getPaymentId());
            pstmt.setString(5, updateReimbursements.getAuthorId());
            pstmt.setString(6, updateReimbursements.getResolverId());
            pstmt.setString(7, updateReimbursements.getStatusId().getStatusId());
            pstmt.setString(8, updateReimbursements.getTypeId().getTypeId());
            pstmt.setString(9, updateReimbursements.getReimbId());

            // TODO allow role to be updated as well

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted != 1) {
                throw new ResourcePersistenceException("Failed to update user data within datasource.");
            }

            conn.commit();

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
    }
}
