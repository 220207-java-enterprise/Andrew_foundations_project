package com.revature.foundations.dtos.requests;

import com.revature.foundations.daos.UserDAO;
import com.revature.foundations.models.ERSUser;
import com.revature.foundations.models.ERSUserRoles;

public class UpdateUserRequest {

    private String userId;
    private String userName;
    private String email;
    private String userPassword;
    private String givenName;
    private String surname;
    private Boolean isActive;
    private String roleId;

    public UpdateUserRequest() {
        super();
    }

    public UpdateUserRequest(String userId, String userName, String email, String userPassword, String givenName,
                             String surname, Boolean isActive, String roleId) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.userPassword = userPassword;
        this.givenName = givenName;
        this.surname = surname;
        this.isActive = isActive;
        this.roleId = roleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public ERSUser extractUser() {
        UserDAO newDAO = new UserDAO();
        ERSUser user = newDAO.getById(this.userId);
        ERSUserRoles newRole = new ERSUserRoles(roleId, user.getRoleId().getRoleOf());
        return new ERSUser(this.userId, this.userName, this.email, this.userPassword, this.givenName, this.surname,
                this.isActive, newRole);
    }

    @Override
    public String toString() {
        return "UpdateUserRequest{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", givenName='" + givenName + '\'' +
                ", surname='" + surname + '\'' +
                ", isActive=" + isActive +
                ", roleId='" + roleId + '\'' +
                '}';
    }
}
