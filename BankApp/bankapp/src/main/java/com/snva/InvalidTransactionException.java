package com.snva;

import java.io.Serializable;

public class InvalidTransactionException extends Exception implements Serializable {
    public InvalidTransactionException(String message) {
        super(message);
    }
}
