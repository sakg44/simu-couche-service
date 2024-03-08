package org.sakg.service;

import org.sakg.exception.BankException;

public interface IOperationService {

    void execute() throws BankException;
}
