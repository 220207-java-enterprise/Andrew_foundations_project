package com.revature.foundations.models;

import java.util.Objects;

public class ERSReimbursementsStatuses {
    private String statusId;
    private String status;

    public ERSReimbursementsStatuses(){
        super();
    }
    public ERSReimbursementsStatuses(String statusId, String status) {
        this.statusId = statusId;
        this.status = status;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ERSReimbursementsStatuses that = (ERSReimbursementsStatuses) o;
        return Objects.equals(statusId, that.statusId) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusId, status);
    }

    @Override
    public String toString() {
        return "ERSReimbursementsStatuses{" +
                "statusId='" + statusId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
