package com.shermatov.emailsender.domain.exception;

public class EmailSendFailedException extends RuntimeException {
    public EmailSendFailedException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
