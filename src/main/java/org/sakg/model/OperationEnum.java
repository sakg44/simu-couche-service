package org.sakg.model;

public enum OperationEnum {
    DEPOSIT("Deposit"),
    WITHDRAWAL("WITHDRAWAL");
    private String value;
    OperationEnum(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }
}
