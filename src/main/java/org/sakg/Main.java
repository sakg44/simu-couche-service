package org.sakg;

import org.sakg.model.Account;
import org.sakg.service.impl.HistoryService;
import org.sakg.service.impl.BankService;
import org.sakg.service.utils.DepositOperation;
import org.sakg.service.utils.WithdrawalOperation;

public class Main {
    public static void main(String[] args) throws Exception {

        Account sakgAccount = new Account();
        DepositOperation operation = new DepositOperation(sakgAccount, 10, HistoryService.getInstance());
        WithdrawalOperation withdrawalOperation = new WithdrawalOperation(sakgAccount, 10, HistoryService.getInstance());
        System.out.println(sakgAccount.getBalance());
        HistoryService.getInstance().showHistory(sakgAccount);

        BankService.getInstance().doOperation(operation);
        System.out.println(sakgAccount.getBalance());
        BankService.getInstance().doOperation(withdrawalOperation);

        HistoryService.getInstance().showHistory(sakgAccount);
    }
}