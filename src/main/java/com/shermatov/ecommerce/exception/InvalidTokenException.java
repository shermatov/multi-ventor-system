package com.shermatov.ecommerce.exception;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException()
    {
        super("Invalid password reset token.");
    }
}
