package org.sakg.model;

import java.util.ArrayList;
import java.util.List;

public class Account {

    List<History> histories;
    private double balance;

    public Account() {
        this.balance = 0.0;
        this.histories = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<History> getHistories() {
        return histories;
    }

    public void setHistories(List<History> histories) {
        this.histories = histories;
    }
}
