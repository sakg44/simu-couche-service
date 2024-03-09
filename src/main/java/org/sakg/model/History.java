package org.sakg.model;

import java.time.OffsetDateTime;

public record History(OperationEnum operationType, OffsetDateTime date, double amount, double balance) {
    
}

