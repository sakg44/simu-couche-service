package org.sakg.service.impl;

import org.sakg.exception.BankException;
import org.sakg.service.IBankService;
import org.sakg.service.IOperationService;

public class BankService implements IBankService {
    private static BankService bankAccountService;
    private BankService() {}


    public static BankService getInstance() {
        if (isNullBankAccountService()) bankAccountService = new BankService();
        return bankAccountService;
    }

    @Override
    public void doTransaction(IOperationService operation) throws BankException {
         operation.execute();
    }

    private static boolean isNullBankAccountService() {
        return bankAccountService == null;
    }
}
