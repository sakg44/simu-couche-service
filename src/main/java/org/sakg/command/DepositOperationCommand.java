package org.sakg.command;

import org.sakg.exception.DepositOperationEception;
import org.sakg.exception.HistoryServiceExeception;
import org.sakg.model.Account;
import org.sakg.model.Operation;
import org.sakg.model.OperationEnum;
import org.sakg.service.IHistoryService;
import org.sakg.utils.BankMessage;

import java.text.MessageFormat;
import java.time.OffsetDateTime;


public class DepositOperationCommand implements IOperationCommand {
    private IHistoryService historyService;
    private Operation operation;


    public DepositOperationCommand(Account account, double amount, IHistoryService historyService) {

        this.historyService = historyService;
        this.operation=new Operation(account,amount,OperationEnum.DEPOSIT,OffsetDateTime.now());
    }

    @Override
    public void execute() throws DepositOperationEception, HistoryServiceExeception {
        depositAmount();


    }



    private void depositAmount() throws DepositOperationEception, HistoryServiceExeception {
        if (isAmountValid()) {
            updateBalance();
            historyService.saveOperationAsHistory(operation);
        } else
            throw new DepositOperationEception(getMessageExceptionForWrongValueDeposit());
    }

    private void updateBalance() {
        operation.getAccount().setBalance(operation.getAccount().getBalance() + operation.getAmount());
    }

    private boolean isAmountValid() {
        return operation.getAmount() > 0;
    }

    private String getMessageExceptionForWrongValueDeposit() {
        return MessageFormat.format(BankMessage.DEPOSIT_VALUE_LESS_OR_EQUAL_ZERO_MESSAGE, String.valueOf(operation.getAmount()));
    }
}
