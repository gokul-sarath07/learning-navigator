package com.crio.learning_navigator.exception;

public class SubjectNotFoundException extends RuntimeException {
    public SubjectNotFoundException() {}

    public SubjectNotFoundException(String message) {
        super(message);
    }
}
