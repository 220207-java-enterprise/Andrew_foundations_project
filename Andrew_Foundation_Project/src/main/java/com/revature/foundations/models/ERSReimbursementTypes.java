package com.revature.foundations.models;

import java.util.Objects;

public class ERSReimbursementTypes {
    private String typeId;
    private String typeOf;

    ERSReimbursementTypes(){
        super();
    }

    ERSReimbursementTypes(String typeId, String typeOf){
        this.typeId = typeId;
        this.typeOf = typeOf;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeOf() {
        return typeOf;
    }

    public void setTypeOf(String typeOf) {
        this.typeOf = typeOf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ERSReimbursementTypes that = (ERSReimbursementTypes) o;
        return Objects.equals(typeId, that.typeId) && Objects.equals(typeOf, that.typeOf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeId, typeOf);
    }

    @Override
    public String toString() {
        return "ERSReimbursementTypes{" +
                "typeId='" + typeId + '\'' +
                ", typeOf='" + typeOf + '\'' +
                '}';
    }
}
