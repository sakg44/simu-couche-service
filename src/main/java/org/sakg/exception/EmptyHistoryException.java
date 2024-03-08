package org.sakg.exception;

import org.sakg.utils.BankMessage;

public class EmptyHistoryException extends HistoryServiceExeception {
    public EmptyHistoryException() {
        super(BankMessage.EMPTY_HISTORY_MESSAGE);
    }
}
