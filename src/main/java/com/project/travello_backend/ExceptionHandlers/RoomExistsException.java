package com.project.travello_backend.ExceptionHandlers;

public class RoomExistsException extends Exception{
    public RoomExistsException() {
    }

    public RoomExistsException(String message) {
        super(message);
    }

    public RoomExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoomExistsException(Throwable cause) {
        super(cause);
    }
}
