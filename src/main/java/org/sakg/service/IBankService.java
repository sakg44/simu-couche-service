package org.sakg.service;

import org.sakg.exception.BankServiceException;

public interface IBankService {
    void doTransaction(IOperation operationService) throws BankServiceException;
}
