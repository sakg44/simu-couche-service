package org.sakg.service.utils;

import org.sakg.exception.DepositOperationEception;
import org.sakg.model.Account;
import org.sakg.model.OperationEnum;
import org.sakg.service.IHistoryService;
import org.sakg.service.IOperationService;
import org.sakg.utils.BankMessage;

import java.text.MessageFormat;


public class DepositOperation implements IOperationService {
    private Account account;
    private double amount;
    private IHistoryService historyService;

    public DepositOperation(Account account, double amount, IHistoryService historyService) {
        this.account = account;
        this.amount = amount;
        this.historyService = historyService;
    }

    @Override
    public void execute() throws DepositOperationEception {
        depositAmount();


    }

    private void depositAmount() throws DepositOperationEception {
        if (isAmountValid()) {
            updateBalance();
            historyService.logOperation(OperationEnum.DEPOSIT, amount, account);
        } else
            throw new DepositOperationEception(getMessageExceptionForWrongValueDeposit());
    }

    private void updateBalance() {
        this.account.setBalance(account.getBalance() + amount);
    }

    private boolean isAmountValid() {
        return amount > 0;
    }

    private String getMessageExceptionForWrongValueDeposit() {
        return MessageFormat.format(BankMessage.DEPOSIT_VALUE_LESS_OR_EQUAL_ZERO_MESSAGE, String.valueOf(amount));
    }
}
