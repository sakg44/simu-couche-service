package org.sakg.service;

import org.sakg.exception.HistoryServiceExeception;
import org.sakg.model.Account;
import org.sakg.model.Operation;

public interface IHistoryService {
    void saveOperationAsHistory(Operation operation) throws HistoryServiceExeception;

    void showAllHistory(Account account) throws HistoryServiceExeception;
}
