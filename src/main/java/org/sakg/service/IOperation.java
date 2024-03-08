package org.sakg.service;

import org.sakg.exception.BankServiceException;

public interface IOperation {
    void execute() throws BankServiceException;
}
