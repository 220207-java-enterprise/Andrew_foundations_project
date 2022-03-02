package com.revature.foundations.daos;

import com.revature.foundations.models.ERSUser;
import com.revature.foundations.models.ERSUserRoles;
import com.revature.foundations.util.ConnectionFactory;
import com.revature.foundations.util.exceptions.DataSourceException;
import com.revature.foundations.util.exceptions.ResourcePersistenceException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements CrudDAO<ERSUser> {

    private final String rootSelect = "SELECT " +
            "eu.USER_ID, eu.USERNAME, eu.EMAIL, eu.USERPASSWORD, eu.GIVEN_NAME, eu.SUR_NAME eu.IS_ACTIVE, eu.ROLE_ID," +
            " ur.ROLEOF " +
            "FROM ERS_USERS eu " +
            "JOIN ERS_USER_ROLES ur " +
            "ON au.ROLE_ID = ur.ROLE_ID ";

    public ERSUser findUserByUsername(String username) {

        ERSUser user = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE username = ?");
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new ERSUser();
                user.setUserId(rs.getString("USER_ID"));
                user.setUserName(rs.getString("USERNAME"));
                user.setEmail(rs.getString("EMAIL"));
                user.setUserPassword(rs.getString("USERPASSWORD"));
                user.setGivenName(rs.getString("GIVEN_NAME"));
                user.setSurname(rs.getString("SUR_NAME"));
                user.setActive(rs.getBoolean("IS_ACTIVE"));
                user.setRoleId(new ERSUserRoles(rs.getString("ROLE_ID"), rs.getString("ROLEOF")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public ERSUser findUserByEmail(String email) {

        ERSUser user = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE email = ?");
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new ERSUser();
                user.setUserId(rs.getString("USER_ID"));
                user.setUserName(rs.getString("USERNAME"));
                user.setEmail(rs.getString("EMAIL"));
                user.setUserPassword(rs.getString("USERPASSWORD"));
                user.setGivenName(rs.getString("GIVEN_NAME"));
                user.setSurname(rs.getString("SUR_NAME"));
                user.setActive(rs.getBoolean("IS_ACTIVE"));
                user.setRoleId(new ERSUserRoles(rs.getString("ROLE_ID"), rs.getString("ROLEOF")));
            }

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }

        return user;

    }

    public ERSUser findUserByUsernameAndPassword(String username, String password) {

        ERSUser authUser = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE username = ? AND password = ?");
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                authUser = new ERSUser();
                authUser.setUserId(rs.getString("USER_ID"));
                authUser.setUserName(rs.getString("USERNAME"));
                authUser.setEmail(rs.getString("EMAIL"));
                authUser.setUserPassword(rs.getString("USERPASSWORD"));
                authUser.setGivenName(rs.getString("GIVEN_NAME"));
                authUser.setSurname(rs.getString("SUR_NAME"));
                authUser.setActive(rs.getBoolean("IS_ACTIVE"));
                authUser.setRoleId(new ERSUserRoles(rs.getString("ROLE_ID"), rs.getString("ROLEOF")));
            }

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }

        return authUser;
    }

    @Override
    public void save(ERSUser newUser) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO app_users VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, newUser.getUserId());
            pstmt.setString(2, newUser.getUserName());
            pstmt.setString(3, newUser.getEmail());
            pstmt.setString(4, newUser.getEmail());
            pstmt.setString(5, newUser.getUserPassword());
            pstmt.setString(6, newUser.getGivenName());
            pstmt.setString(7, newUser.getSurname());
            pstmt.setBoolean(8,newUser.getActive());
            pstmt.setString(9, newUser.getRoleId().getRoleId());

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
    public ERSUser getById(String id) {

        ERSUser user = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE id = ?");
            pstmt.setString(1, id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new ERSUser();
                user.setUserId(rs.getString("USER_ID"));
                user.setUserName(rs.getString("USERNAME"));
                user.setEmail(rs.getString("EMAIL"));
                user.setUserPassword(rs.getString("USERPASSWORD"));
                user.setGivenName(rs.getString("GIVEN_NAME"));
                user.setSurname(rs.getString("SUR_NAME"));
                user.setActive(rs.getBoolean("IS_ACTIVE"));
                user.setRoleId(new ERSUserRoles(rs.getString("ROLE_ID"), rs.getString("ROLEOF")));
            }

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }

        return user;

    }

    @Override
    public List<ERSUser> getAll() {

        List<ERSUser> users = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            ResultSet rs = conn.createStatement().executeQuery(rootSelect);
            while (rs.next()) {
                ERSUser user = new ERSUser();
                user.setUserId(rs.getString("USER_ID"));
                user.setUserName(rs.getString("USERNAME"));
                user.setEmail(rs.getString("EMAIL"));
                user.setUserPassword(rs.getString("USERPASSWORD"));
                user.setGivenName(rs.getString("GIVEN_NAME"));
                user.setSurname(rs.getString("SUR_NAME"));
                user.setActive(rs.getBoolean("IS_ACTIVE"));
                user.setRoleId(new ERSUserRoles(rs.getString("ROLE_ID"), rs.getString("ROLEOF")));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DataSourceException(e);
        }

        return users;
    }

    @Override
    public void update(ERSUser updatedUser) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement("UPDATE ERS_USERS " +
                    "SET USERNAME = ?, " +
                    "EMAIL = ?, " +
                    "USERPASSWORD = ?, " +
                    "GIVEN_NAME = ?, " +
                    "SUR_NAME = ? " +
                    "IS_ACTIVE = ? " +
                    "WHERE id = ?");
            pstmt.setString(1, updatedUser.getUserName());
            pstmt.setString(2, updatedUser.getEmail());
            pstmt.setString(3, updatedUser.getUserPassword());
            pstmt.setString(4, updatedUser.getGivenName());
            pstmt.setString(5, updatedUser.getSurname());
            pstmt.setBoolean(6, updatedUser.getActive());
            pstmt.setString(7, updatedUser.getUserId());

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

    //@Override
    public void deleteById(String id) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM app_users WHERE id = ?");
            pstmt.setString(1, id);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted != 1) {
                conn.rollback();
                throw new ResourcePersistenceException("Failed to delete user from data source");
            }

            conn.commit();

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
    }
}
