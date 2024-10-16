package com.crio.learning_navigator.exception;

public class ExamNotFoundException extends RuntimeException {
    public ExamNotFoundException() {}

    public ExamNotFoundException(String message) {
        super(message);
    }
}
