package org.sakg.command;

import org.sakg.exception.BankServiceException;
import org.sakg.exception.HistoryServiceExeception;

public interface IOperationCommand {
    void execute() throws BankServiceException, HistoryServiceExeception;
}
