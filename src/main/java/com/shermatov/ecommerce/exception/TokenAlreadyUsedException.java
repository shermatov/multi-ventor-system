package com.shermatov.ecommerce.exception;

public class TokenAlreadyUsedException extends RuntimeException {
    public TokenAlreadyUsedException() {
        super("Password reset token has already been used.");
    }
}
