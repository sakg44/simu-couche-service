package org.sakg.service;

import org.sakg.model.Account;
import org.sakg.model.History;
import org.sakg.model.OperationEnum;

public interface IHistoryService {
    void logOperation(OperationEnum operation, double amount, Account account);

    void addHistory(Account account, History history);
}
