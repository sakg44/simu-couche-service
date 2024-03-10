package org.sakg.service.impl;

import org.sakg.exception.EmptyHistoryException;
import org.sakg.exception.HistoryServiceExeception;
import org.sakg.mapper.HistoryMapper;
import org.sakg.model.Account;
import org.sakg.model.History;
import org.sakg.model.Operation;
import org.sakg.service.IHistoryService;
import org.sakg.utils.BankMessage;

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

    private static void checkNull(History history) throws HistoryServiceExeception {
        if (history == null)
            throw new HistoryServiceExeception(BankMessage.CANT_SAVE_NULL_OPERATION_AS_HISTORY);
    }

    public void saveOperationAsHistory(Operation operation) throws HistoryServiceExeception {
        History history = HistoryMapper.fromOperation(operation);
        checkNull(history);
        addHistory(operation.getAccount(), history);
    }
    private void addHistory(Account account, History history) {
        account.getHistories().add(0,history);
    }

    @Override
    public void showAllHistory(Account account) throws HistoryServiceExeception {
        if (hasHistory(account)) {
            throw new EmptyHistoryException();
        }
        logger.info("******* Voici l'historique de votre Compte *********");
        account.getHistories().stream().map(History::toString).forEach(System.out::println);
    }

}
