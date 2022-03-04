package com.revature.foundations.dtos.responses;

import com.revature.foundations.models.ERSUser;

public class Principal {
    private String userId;
    private String userName;
    private String roleOf;

    public Principal() {
        super();
    }

    public Principal(ERSUser user){
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.roleOf = user.getRoleId().getRoleOf();
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

    public String getRoleId() {
        return roleOf;
    }

    public void setRoleId(String roleId) {
        this.roleOf = roleId;
    }

    @Override
    public String toString() {
        return "Principal{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", roleId='" + roleOf + '\'' +
                '}';
    }
}
