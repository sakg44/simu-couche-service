package org.sakg;

import org.sakg.command.DepositOperationCommand;
import org.sakg.command.WithdrawalOperationCommand;
import org.sakg.model.Account;
import org.sakg.service.impl.BankService;
import org.sakg.service.impl.HistoryService;


public class Main {
    public static void main(String[] args) throws Exception {

        Account sakgAccount = new Account();
        DepositOperationCommand operation = new DepositOperationCommand(sakgAccount, 10, HistoryService.getInstance());
        WithdrawalOperationCommand withdrawalOperation = new WithdrawalOperationCommand(sakgAccount, 10, HistoryService.getInstance());
        HistoryService.getInstance().showHistory(sakgAccount);

        BankService.getInstance().doTransaction(operation);
        BankService.getInstance().doTransaction(withdrawalOperation);

        HistoryService.getInstance().showHistory(sakgAccount);
    }
}