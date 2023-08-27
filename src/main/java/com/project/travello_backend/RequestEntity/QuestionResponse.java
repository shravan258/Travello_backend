package com.project.travello_backend.RequestEntity;

public class QuestionResponse {

    private Boolean isFromServer;
    private String message;

    public QuestionResponse() {
    }

    public QuestionResponse(Boolean isFromServer, String message) {
        this.isFromServer = isFromServer;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getFromServer() {
        return isFromServer;
    }

    public void setFromServer(Boolean fromServer) {
        isFromServer = fromServer;
    }

    public void concatenateMessage(String anothermessage){

       if(anothermessage!= null){
           message+=anothermessage;
       }
    }

    @Override
    public String toString() {
        return "QuestionResponse{" +
                "isFromServer=" + isFromServer +
                ", message='" + message + '\'' +
                '}';
    }
}
