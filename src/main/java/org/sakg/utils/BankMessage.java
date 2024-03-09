package org.sakg.utils;

public class BankMessage {


    public static final String CANT_SAVE_NULL_OPERATION_AS_HISTORY = "Operation objet is null" ;

    private BankMessage(){
        throw new IllegalCallerException("CONSTRUCTOR SHOULD NOT BE CALL");
    }
    public static final String EMPTY_HISTORY_MESSAGE = "Vous n'avez pas d'opération.";

    public static final String WITHDRAWAL_NOT_ENOUGH_FUND = "Votre Solde est: {0}, vous ne pouvez par retirer {1}.";
    public  static final String WITHDRAWAL_VALUE_LESS_OR_EQUAL_ZERO_MESSAGE="Impossible de retirer une valeur({0}) negatif ou null.";

    public  static final String DEPOSIT_VALUE_LESS_OR_EQUAL_ZERO_MESSAGE="Impossible de deposer une valeur({0}) negatif ou null.";
}
