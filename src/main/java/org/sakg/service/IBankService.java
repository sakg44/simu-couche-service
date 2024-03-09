package org.sakg.service;

import org.sakg.command.IOperationCommand;
import org.sakg.exception.BankServiceException;
import org.sakg.exception.HistoryServiceExeception;

public interface IBankService {
    void doTransaction(IOperationCommand operation) throws BankServiceException, HistoryServiceExeception;
}
