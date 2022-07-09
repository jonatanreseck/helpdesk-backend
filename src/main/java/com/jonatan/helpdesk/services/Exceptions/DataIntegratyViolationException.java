package com.jonatan.helpdesk.services.Exceptions;

public class DataIntegratyViolationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DataIntegratyViolationException(String message) {
        super(message);
    }

    public DataIntegratyViolationException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
