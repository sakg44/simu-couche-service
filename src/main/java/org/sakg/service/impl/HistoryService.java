package org.sakg.service.impl;

import org.sakg.model.Account;
import org.sakg.model.History;
import org.sakg.model.OperationEnum;
import org.sakg.service.IHistoryService;
import org.sakg.utils.BankMessage;

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

    public void logOperation(OperationEnum operation, double amount, Account account) {
        History history = createHistory(operation, OffsetDateTime.now(), amount, account.getBalance());
        addHistory(account, history);
    }

    private History createHistory(OperationEnum operation, OffsetDateTime date, double amount, double balance) {
        return new History(operation, date, amount, balance);
    }

    public void addHistory(Account account, History history) {
        account.getHistories().add(history);
    }

    public void showHistory(Account account) {
        if (hasHistory(account)) {
            logger.info(BankMessage.EMPTY_HISTORY_MESSAGE);
        }
        logger.info(account.getHistories().toString());

    }

}
