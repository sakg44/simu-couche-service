package org.sakg.service.utils;

import org.sakg.exception.WithdrawalException;
import org.sakg.model.Account;
import org.sakg.model.Operation;
import org.sakg.model.OperationEnum;
import org.sakg.service.IHistoryService;
import org.sakg.service.IOperation;
import org.sakg.utils.BankMessage;

import java.text.MessageFormat;
import java.time.OffsetDateTime;

public class WithdrawalOperation implements IOperation {
    private final IHistoryService historyService;
    private Operation operation;

    public WithdrawalOperation(Account account, double amount, IHistoryService historyService) {

        this.historyService = historyService;
        this.operation=new Operation(account,amount,OperationEnum.WITHDRAWAL,OffsetDateTime.now());
    }

    @Override
    public void execute() throws WithdrawalException {
        withDrawalAmount();

    }

    private void checkAmountToWithrawal() throws WithdrawalException {
        if (!isAmountValid())
            throw new WithdrawalException(getMessageForInvalideAmount());
    }

    private String getMessageForInvalideAmount() {
        return MessageFormat.format(BankMessage.WITHDRAWAL_VALUE_LESS_OR_EQUAL_ZERO_MESSAGE, String.valueOf(operation.getAmount()));

    }

    private void withDrawalAmount() throws WithdrawalException {
        checkAmountToWithrawal();
        if (isPossibleToWithdrawal()) {
            operation.getAccount().setBalance(operation.getAccount().getBalance() - operation.getAmount());
            historyService.saveOperationAsHistory(operation);
        } else
            throw new WithdrawalException(getMessageExceptionForNotEnoughFund());
    }

    private String getMessageExceptionForNotEnoughFund() {
        return MessageFormat.format(BankMessage.WITHDRAWAL_NOT_ENOUGH_FUND, String.valueOf(operation.getAccount().getBalance()), String.valueOf(operation.getAmount()));
    }

    private boolean isPossibleToWithdrawal() {
        return operation.getAccount().getBalance() >= operation.getAmount();
    }

    private boolean isAmountValid() {
        return operation.getAmount() > 0;
    }

}
