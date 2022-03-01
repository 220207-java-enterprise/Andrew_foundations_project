package com.revature.foundations.models;

import java.util.Objects;

public class ERSUser {

    private String userId;
    private String userName;
    private String email;
    private String userPassword;
    private String givenName;
    private Boolean isActive;
    private String roleId;

    public ERSUser() {
        super();
    }

    public ERSUser(String userId, String userName, String email, String userPassword, String givenName,
                   Boolean isActive, String roleId){
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.userPassword = userPassword;
        this.givenName = givenName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ERSUser ersUser = (ERSUser) o;
        return Objects.equals(userId, ersUser.userId)
                && Objects.equals(userName, ersUser.userName)
                && Objects.equals(email, ersUser.email)
                && Objects.equals(userPassword, ersUser.userPassword)
                && Objects.equals(givenName, ersUser.givenName)
                && Objects.equals(isActive, ersUser.isActive)
                && Objects.equals(roleId, ersUser.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, email, userPassword, givenName, isActive, roleId);
    }

    @Override
    public String toString() {
        return "ERSUser{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", givenName='" + givenName + '\'' +
                ", isActive=" + isActive +
                ", roleId='" + roleId + '\'' +
                '}';
    }
}
