package com.project.travello_backend.RequestEntity;

public class ErrorEntity {
    private String error;
    private String message;

    public ErrorEntity() {
    }

    public ErrorEntity(String error, String message) {
        this.error = error;
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorEntity{" +
                "error='" + error + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
