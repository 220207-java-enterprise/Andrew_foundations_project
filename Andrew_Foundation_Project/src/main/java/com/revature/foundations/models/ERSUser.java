package com.revature.foundations.models;

import java.util.Objects;

public class ERSUser {

    private String userId;
    private String userName;
    private String email;
    private String password;
    private String givenName;
    private String surname;
    private Boolean isActive;
    private ERSUserRoles roleId;

    public ERSUser() {
        super();
    }


    public ERSUser(String userId, String userName, String email, String userPassword, String givenName, String surname) {

        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = userPassword;
        this.givenName = givenName;
        this.surname = surname;
    }

    public ERSUser(String userId, String userName, String email, String password, String givenName, String surname,
                   Boolean isActive, ERSUserRoles roleId) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public ERSUserRoles getRoleId() {
        return roleId;
    }

    public void setRoleId(ERSUserRoles roleId) {
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
                && Objects.equals(password, ersUser.password)
                && Objects.equals(givenName, ersUser.givenName)
                && Objects.equals(surname, ersUser.surname)
                && Objects.equals(isActive, ersUser.isActive)
                && Objects.equals(roleId, ersUser.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, email, password, givenName, surname, isActive, roleId);
    }

    @Override
    public String toString() {
        return "ERSUser{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", userPassword='" + password + '\'' +
                ", givenName='" + givenName + '\'' +
                ", surname='" + surname + '\'' +
                ", isActive=" + isActive +
                ", roleId=" + roleId +
                '}';
    }
}
