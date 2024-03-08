package org.sakg.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sakg.exception.BankServiceException;
import org.sakg.exception.DepositOperationEception;
import org.sakg.exception.WithdrawalException;
import org.sakg.model.Account;
import org.sakg.service.impl.BankService;
import org.sakg.service.impl.HistoryService;
import org.sakg.service.utils.DepositOperation;
import org.sakg.service.utils.WithdrawalOperation;

import static java.lang.String.valueOf;
import static java.text.MessageFormat.format;

@DisplayName("Test bank service operation of Deposit and Withdrawal")
class BankServiceTest {

    Account myAccount;
    IBankService bankService;
    IHistoryService historyService;

    @BeforeEach
    void initBackAccount() {
        this.bankService = BankService.getInstance();
        this.historyService = HistoryService.getInstance();
        this.myAccount = new Account();
    }

    @Test
    @DisplayName("Should deposit money to account")
    void shouldDepositMoneyToAccount() throws BankServiceException {
        //Given
        int amount = 10;
        DepositOperation depositOperation = new DepositOperation(myAccount, amount, historyService);

        //When
        bankService.doTransaction(depositOperation);

        //Then
        Assertions.assertEquals(amount, myAccount.getBalance());
    }

    @Test
    @DisplayName("Should failed: Can't deposit amount 0")
    void should_failed_deposit_money_to_account_with_value_equal_to_zero() {
        //Given
        int amount = 0;
        String message = format("Impossible de deposer une valeur({0}) negatif ou null.", valueOf(amount));
        DepositOperation depositOperation = new DepositOperation(myAccount, amount, historyService);
        //When & Then
        Assertions.assertThrowsExactly(DepositOperationEception.class, () -> bankService.doTransaction(depositOperation), message);
    }

    @Test
    @DisplayName("Should failed: Can't deposit amount less than 0")
    void should_failed_deposit_money_to_account_with_value_less_than_zero() {
        //Given
        int amount = -10;
        String message = format("Impossible de deposer une valeur({0}) negatif ou null.", valueOf(amount));
        DepositOperation depositOperation = new DepositOperation(myAccount, amount, historyService);
        //When & Then
        Assertions.assertThrowsExactly(DepositOperationEception.class, () -> bankService.doTransaction(depositOperation), message);

    }


    @Test
    @DisplayName("Should withdrawal money from account.")
    void should_withdrawal_money_from_account() throws BankServiceException {
        //Given
        double defaultBalance = 50.0;
        int amountToWithdrawal = 10;
        myAccount.setBalance(defaultBalance);
        WithdrawalOperation withdrawalOperation = new WithdrawalOperation(myAccount, amountToWithdrawal, HistoryService.getInstance());

        //When
        bankService.doTransaction(withdrawalOperation);

        //Then
        double exceptedBalance = defaultBalance - amountToWithdrawal;
        Assertions.assertEquals(exceptedBalance, myAccount.getBalance());
    }

    @Test
    @DisplayName("Should failed: Can't withdrawal amount 0 ")
    void should_failed_withdrawal_money_from_account_with_value_equal_to_zero() {
        //Given
        int amountToWithdrawal = 0;
        String message = format("Impossible de retirer une valeur({0}) negatif ou null.", valueOf(myAccount.getBalance()), valueOf(10));
        WithdrawalOperation withdrawalOperation = new WithdrawalOperation(myAccount, amountToWithdrawal, HistoryService.getInstance());

        //When & Then

        Assertions.assertThrowsExactly(WithdrawalException.class, () -> bankService.doTransaction(withdrawalOperation), message);

    }

    @Test
    @DisplayName("Should failed: Can't withdrawal amount less than 0 ")
    void should_failed_withdrawal_money_from_account_with_value_less_than_zero() {
        //Given
        int amountToWithdrawal = -10;
        String message = format("Impossible de retirer une valeur({0}) negatif ou null.", valueOf(myAccount.getBalance()), valueOf(10));
        WithdrawalOperation withdrawalOperation = new WithdrawalOperation(myAccount, amountToWithdrawal, HistoryService.getInstance());
        //When & Then
        Assertions.assertThrowsExactly(WithdrawalException.class, () -> bankService.doTransaction(withdrawalOperation), message);

    }

    @Test
    @DisplayName("Should failed: After withdrawal your balance should be greater or equal to 0 ")
    void should_failed_withdrawal_money_from_account_amount_to_high() {
        //Given
        int amountToWithdrawal = 10;
        String message = format("Votre Solde est: {0}, vous ne pouvez par retirer {1}", valueOf(myAccount.getBalance()), valueOf(10));
        WithdrawalOperation withdrawalOperation = new WithdrawalOperation(myAccount, amountToWithdrawal, HistoryService.getInstance());

        //When & Then
        Assertions.assertThrowsExactly(WithdrawalException.class, () -> bankService.doTransaction(withdrawalOperation), message);

    }

}
