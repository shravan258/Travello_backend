package com.project.travello_backend.RequestEntity;

public class MessageResponse {
    private String username;
    private String message;

    public MessageResponse() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
