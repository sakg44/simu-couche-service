package org.sakg.service.utils;

import org.sakg.exception.DepositOperationEception;
import org.sakg.model.Account;
import org.sakg.model.Operation;
import org.sakg.model.OperationEnum;
import org.sakg.service.IHistoryService;
import org.sakg.service.IOperation;
import org.sakg.utils.BankMessage;

import java.text.MessageFormat;
import java.time.OffsetDateTime;


public class DepositOperation implements IOperation {
    private IHistoryService historyService;
    private Operation operation;


    public DepositOperation(Account account, double amount, IHistoryService historyService) {

        this.historyService = historyService;
        this.operation=new Operation(account,amount,OperationEnum.DEPOSIT,OffsetDateTime.now());
    }

    @Override
    public void execute() throws DepositOperationEception {
        depositAmount();


    }



    private void depositAmount() throws DepositOperationEception {
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
