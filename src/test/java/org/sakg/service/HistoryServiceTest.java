package org.sakg.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sakg.exception.EmptyHistoryException;
import org.sakg.model.Account;
import org.sakg.model.History;
import org.sakg.model.OperationEnum;
import org.sakg.service.impl.HistoryService;
import org.sakg.service.utils.DepositOperation;
import org.sakg.utils.BankMessage;

import java.time.OffsetDateTime;
import java.util.List;

@DisplayName(" History service test")
class HistoryServiceTest {
    IHistoryService historyService;
    Account myAccount;
    IOperation operation;
    int amout=10;
    @BeforeEach
    public void init(){
        historyService= HistoryService.getInstance();
        this.myAccount=new Account();
        operation=new DepositOperation(myAccount,amout,this.historyService);
    }

    @Test
    @DisplayName("Should show existing histories")
    void should_show_histories(){
        //Given
        OffsetDateTime date=OffsetDateTime.now();
        double balance=amout+myAccount.getBalance();
        History history= new History(OperationEnum.DEPOSIT,date,amout,balance);
        myAccount.setHistories(List.of(history));
        //When && Then
        Assertions.assertDoesNotThrow(()->historyService.showHistory(myAccount));
    }
    @Test
    @DisplayName("Should throw empty history")
    void should_throw_empty_history_exception(){
        //Given
        //When && Then
        Assertions.assertThrowsExactly(EmptyHistoryException.class,()->historyService.showHistory(myAccount), BankMessage.EMPTY_HISTORY_MESSAGE);
    }
}
