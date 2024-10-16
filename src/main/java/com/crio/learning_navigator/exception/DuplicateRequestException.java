package com.crio.learning_navigator.exception;

public class DuplicateRequestException extends RuntimeException {
    public DuplicateRequestException() {}

    public DuplicateRequestException(String message) {
        super(message);
    }
}
