package com.university.CLI.exception;

public class InvalidIDException extends RuntimeException {
    public InvalidIDException(String message) {
        super(message);
    }
}
