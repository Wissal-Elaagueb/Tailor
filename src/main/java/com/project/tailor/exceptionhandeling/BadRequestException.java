package com.project.tailor.exceptionhandeling;

public class BadRequestException extends Exception{
    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }
}
