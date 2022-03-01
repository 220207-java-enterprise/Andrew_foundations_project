package com.revature.foundations.models;

import java.util.Objects;

public class ERSUserRoles {
    private String roleId;
    private String roleOf;

    public ERSUserRoles() {
        super();
    }

    public ERSUserRoles(String roleId, String roleOf){
        this.roleId = roleId;
        this.roleOf = roleOf;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleOf() {
        return roleOf;
    }

    public void setRoleOf(String roleOf) {
        this.roleOf = roleOf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ERSUserRoles that = (ERSUserRoles) o;
        return Objects.equals(roleId, that.roleId)
                && Objects.equals(roleOf, that.roleOf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleOf);
    }

    @Override
    public String toString() {
        return "ERSUserRoles{" +
                "roleId='" + roleId + '\'' +
                ", roleOf='" + roleOf + '\'' +
                '}';
    }
}
