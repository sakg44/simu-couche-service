package org.sakg.model;

import java.time.OffsetDateTime;

public record History(OperationEnum operation, OffsetDateTime date, double amount, double balance) {
    
}
