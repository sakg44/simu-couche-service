package org.sakg.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sakg.exception.EmptyHistoryException;
import org.sakg.exception.HistoryServiceExeception;
import org.sakg.mapper.HistoryMapper;
import org.sakg.model.Account;
import org.sakg.model.History;
import org.sakg.model.Operation;
import org.sakg.model.OperationEnum;
import org.sakg.service.impl.HistoryService;
import org.sakg.utils.BankMessage;

import java.time.OffsetDateTime;
import java.util.List;

@DisplayName("********* History service test *********")
class HistoryServiceTest {
    static OffsetDateTime dateOfOperation = OffsetDateTime.now();
    IHistoryService historyService;
    Account myAccount;
    Operation operation;
    int amout = 10;

    @BeforeEach
    public void init() {
        historyService = HistoryService.getInstance();
        this.myAccount = new Account();
        operation = new Operation(myAccount, amout, OperationEnum.DEPOSIT, dateOfOperation);
    }

    @Test
    @DisplayName("Should show existing histories")
    void shouldShowHistories() {
        //Given
        OffsetDateTime date = OffsetDateTime.now();
        double balance = amout + myAccount.getBalance();
        History history = new History(OperationEnum.DEPOSIT, date, amout, balance);
        myAccount.setHistories(List.of(history));
        //When && Then
        Assertions.assertDoesNotThrow(() -> historyService.showAllHistory(myAccount));
    }

    @Test
    @DisplayName("Should throw exception empty history")
    void shouldThrowEmptyHistoryException() {
        //Given
        //When && Then
        Assertions.assertThrowsExactly(EmptyHistoryException.class, () -> historyService.showAllHistory(myAccount), BankMessage.EMPTY_HISTORY_MESSAGE);
    }

    @Test
    @DisplayName("Should save operation as history")
    void shouldSaveOperationAsHistory() throws HistoryServiceExeception {
        //When
        this.historyService.saveOperationAsHistory(operation);
        //Then
        Assertions.assertEquals(1, myAccount.getHistories().size());
        Assertions.assertEquals(HistoryMapper.fromOperation(operation), myAccount.getHistories().get(0));

    }

    @Test
    @DisplayName("Throw exception when saving null operation as history")
    void shouldThrowExceptionWhenSavingNullOperationAsHistory() {
        //Given
        Operation nullOperation = null;
        //When &        //Then
        Assertions.assertThrowsExactly(HistoryServiceExeception.class, () -> historyService.saveOperationAsHistory(nullOperation), BankMessage.CANT_SAVE_NULL_OPERATION_AS_HISTORY);

    }
}
