package org.sakg.service.utils;

import org.sakg.exception.WithdrawalException;
import org.sakg.model.Account;
import org.sakg.model.OperationEnum;
import org.sakg.service.IHistoryService;
import org.sakg.service.IOperationService;
import org.sakg.utils.BankMessage;

import java.text.MessageFormat;

public class WithdrawalOperation implements IOperationService {
    private final IHistoryService historyService;
    private Account account;
    private double amount;

    public WithdrawalOperation(Account account, double amount, IHistoryService historyService) {
        this.account = account;
        this.amount = amount;
        this.historyService = historyService;
    }

    @Override
    public void execute() throws WithdrawalException {
        if (isPossibleToWithdrawal())
            withDrawalAmount();
        else
            throw new WithdrawalException(getMessageExceptionForNotEnoughFund());
    }

    private void withDrawalAmount() {
        account.setBalance(account.getBalance() - amount);
        historyService.logOperation(OperationEnum.WITHDRAWAL, amount, account);
    }

    private String getMessageExceptionForNotEnoughFund() {
        return MessageFormat.format(BankMessage.WITHDRAWAL_NOT_ENOUGH_FUND, String.valueOf(account.getBalance()), String.valueOf(amount));
    }

    private boolean isPossibleToWithdrawal() {
        return account.getBalance() >= amount;
    }
}
