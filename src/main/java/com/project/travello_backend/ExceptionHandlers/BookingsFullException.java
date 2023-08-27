package com.project.travello_backend.ExceptionHandlers;

public class BookingsFullException extends Exception{
    public BookingsFullException() {
    }

    public BookingsFullException(String message) {
        super(message);
    }

    public BookingsFullException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookingsFullException(Throwable cause) {
        super(cause);
    }
}
