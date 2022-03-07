package com.revature.foundations.daos;

import com.revature.foundations.models.ERSUser;
import com.revature.foundations.models.ERSUserRoles;
import com.revature.foundations.util.ConnectionFactory;
import com.revature.foundations.util.exceptions.DataSourceException;
import com.revature.foundations.util.exceptions.ResourcePersistenceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements CrudDAO<ERSUser> {

    private final String rootSelect = "SELECT ers_users.user_id, " +
            "ers_users.username, " +
            "ers_users.email, " +
            "ers_users.userpassword, " +
            "ers_users.given_name, " +
            "ers_users.sur_name, " +
            "ers_users.is_active, " +
            "ers_users.role_id, " +
            "ers_user_roles.roleof " +
            "FROM ers_users " +
            "JOIN ers_user_roles " +
            "ON ers_users.role_id = ers_user_roles.role_id ";

    public ERSUser findUserByUsername(String username) {

        ERSUser user = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE username = ?");
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new ERSUser();
                user.setUserId(rs.getString("user_id"));
                user.setUserName(rs.getString("userName"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("userPassword"));
                user.setGivenName(rs.getString("given_name"));
                user.setSurname(rs.getString("sur_name"));
                user.setActive(rs.getBoolean("is_active"));
                user.setRoleId(new ERSUserRoles(rs.getString("role_id"), rs.getString("roleOf")));
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
                user.setUserId(rs.getString("user_id"));
                user.setUserName(rs.getString("userName"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("userPassword"));
                user.setGivenName(rs.getString("given_name"));
                user.setSurname(rs.getString("sur_name"));
                user.setActive(rs.getBoolean("is_active"));
                user.setRoleId(new ERSUserRoles(rs.getString("role_id"), rs.getString("roleOf")));
            }

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }

        return user;

    }

    public ERSUser findUserByUsernameAndPassword(String username, String password) {

        ERSUser authUser = null;
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE username = ? AND userpassword = ?");
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery(); //changed schema to public
            if (rs.next()) {

                authUser = new ERSUser();
                authUser.setUserId(rs.getString("user_id"));
                authUser.setUserName(rs.getString("userName"));
                authUser.setEmail(rs.getString("email"));
                authUser.setPassword(rs.getString("userPassword"));
                authUser.setGivenName(rs.getString("given_name"));
                authUser.setSurname(rs.getString("sur_name"));
                authUser.setActive(rs.getBoolean("is_active"));
                authUser.setRoleId(new ERSUserRoles(rs.getString("role_id"), rs.getString("roleOf")));
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
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ers_users VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, newUser.getUserId());
            pstmt.setString(2, newUser.getUserName());
            pstmt.setString(3, newUser.getEmail());
            pstmt.setString(4, newUser.getPassword());
            pstmt.setString(5, newUser.getGivenName());
            pstmt.setString(6, newUser.getSurname());
            pstmt.setBoolean(7, newUser.getActive());
            pstmt.setString(8, newUser.getRoleId().getRoleOf());

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

            PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE user_id = ?");
            pstmt.setString(1, id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new ERSUser();
                user.setUserId(rs.getString("user_id"));
                user.setUserName(rs.getString("userName"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("userPassword"));
                user.setGivenName(rs.getString("given_name"));
                user.setSurname(rs.getString("sur_name"));
                user.setActive(rs.getBoolean("is_active"));
                user.setRoleId(new ERSUserRoles(rs.getString("role_id"), rs.getString("roleOf")));
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
                user.setPassword(rs.getString("USERPASSWORD"));
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
                    "SUR_NAME = ?, " +
                    "IS_ACTIVE = ?, " +
                    "ROLE_ID = ? " +
                    "WHERE User_ID = ?");

            pstmt.setString(1, updatedUser.getUserName());
            pstmt.setString(2, updatedUser.getEmail());
            pstmt.setString(3, updatedUser.getPassword());
            pstmt.setString(4, updatedUser.getGivenName());
            pstmt.setString(5, updatedUser.getSurname());
            pstmt.setBoolean(6, updatedUser.getActive());
            pstmt.setString(7, updatedUser.getRoleId().getRoleId());
            pstmt.setString(8, updatedUser.getUserId());

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
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ers_users WHERE user_id = ?");
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
