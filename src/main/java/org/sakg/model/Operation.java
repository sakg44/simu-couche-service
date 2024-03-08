package org.sakg.model;

import java.time.OffsetDateTime;

public class Operation {
    private Account account;
    private double amount;
    private OperationEnum operationType;
    private OffsetDateTime date;

    public Operation(Account account, double amount, OperationEnum operationType, OffsetDateTime date) {
        this.account = account;
        this.amount = amount;
        this.operationType = operationType;
        this.date = date;
    }

    public Account getAccount() {
        return account;
    }

    public double getAmount() {
        return amount;
    }


    public OffsetDateTime getDate() {
        return date;
    }


    public OperationEnum getOperationType() {
        return operationType;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "account=" + account +
                ", amount=" + amount +
                ", OperationType=" + operationType +
                ", date=" + date +
                '}';
    }
}
