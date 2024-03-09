package org.sakg;

import org.sakg.command.DepositOperationCommand;
import org.sakg.command.WithdrawalOperationCommand;
import org.sakg.model.Account;
import org.sakg.service.impl.BankService;
import org.sakg.service.impl.HistoryService;


public class Main {
    public static void main(String[] args) throws Exception {

        Account sakgAccount = new Account();

        // ***** Operation *********
        DepositOperationCommand depositOperation = new DepositOperationCommand(sakgAccount, 10, HistoryService.getInstance());
        WithdrawalOperationCommand withdrawalOperation = new WithdrawalOperationCommand(sakgAccount, 10, HistoryService.getInstance());

        //******  Do transaction ********
        BankService.getInstance().doTransaction(depositOperation);
        BankService.getInstance().doTransaction(withdrawalOperation);

        //****** Show State
        HistoryService.getInstance().showHistory(sakgAccount);
    }
}