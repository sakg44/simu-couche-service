package org.sakg.service.impl;

import org.sakg.exception.EmptyHistoryException;
import org.sakg.exception.HistoryServiceExeception;
import org.sakg.model.Account;
import org.sakg.model.History;
import org.sakg.model.Operation;
import org.sakg.model.OperationEnum;
import org.sakg.service.IHistoryService;

import java.time.OffsetDateTime;
import java.util.logging.Logger;

public class HistoryService implements IHistoryService {

    private static Logger logger = Logger.getLogger(HistoryService.class.getSimpleName());
    private static HistoryService historyService;

    private HistoryService() {

    }

    public static HistoryService getInstance() {
        if (historyService == null)
            historyService = new HistoryService();
        return historyService;
    }

    private static boolean hasHistory(Account account) {
        return account.getHistories().isEmpty();
    }

    public void saveOperationAsHistory(Operation operation) {
        History history = createHistory(operation.getOperationType(), operation.getDate(), operation.getAmount(), operation.getAccount().getBalance());
        addHistory(operation.getAccount(), history);
    }

    private History createHistory(OperationEnum operation, OffsetDateTime date, double amount, double balance) {
        return new History(operation, date, amount, balance);
    }

    private void addHistory(Account account, History history) {
        account.getHistories().add(history);
    }

    @Override
    public void showHistory(Account account) throws HistoryServiceExeception {
        if (hasHistory(account)) {
            throw new EmptyHistoryException();
        }
        logger.info(account.getHistories().toString());

    }

}
