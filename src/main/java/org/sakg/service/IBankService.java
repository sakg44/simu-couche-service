package org.sakg.service;

import org.sakg.exception.BankException;

public interface IBankService {
    void doTransaction(IOperationService operationService) throws BankException;
}
