package com.crio.learning_navigator.exception;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException() {}

    public StudentNotFoundException(String message) {
        super(message);
    }
}
