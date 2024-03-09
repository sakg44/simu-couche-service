package org.sakg.mapper;

import org.sakg.model.History;
import org.sakg.model.Operation;

public class HistoryMapper {
    private HistoryMapper(){

    }
    public static History fromOperation(Operation operation){
            if(isNull(operation))
                return null;
            return  new History(operation.getOperationType(), operation.getDate(), operation.getAmount(), operation.getAccount().getBalance());

    }

    private static boolean isNull(Operation operation) {
        return operation == null;
    }
}
