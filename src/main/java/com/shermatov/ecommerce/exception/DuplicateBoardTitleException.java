package com.shermatov.ecommerce.exception;

public class DuplicateBoardTitleException extends RuntimeException {

    public DuplicateBoardTitleException(String title) {
        super("Board title already exists: " + title);
    }
}
