package org.sakg.service.impl;

import org.sakg.exception.BankServiceException;
import org.sakg.exception.HistoryServiceExeception;
import org.sakg.service.IBankService;
import org.sakg.command.IOperationCommand;

public class BankService implements IBankService {
    private static BankService bankAccountService;
    private BankService() {}


    public static BankService getInstance() {
        if (isNullBankAccountService()) bankAccountService = new BankService();
        return bankAccountService;
    }

    @Override
    public void doTransaction(IOperationCommand operation) throws BankServiceException, HistoryServiceExeception {
         operation.execute();
    }

    private static boolean isNullBankAccountService() {
        return bankAccountService == null;
    }
}
