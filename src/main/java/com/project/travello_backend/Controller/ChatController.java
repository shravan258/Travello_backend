package com.project.travello_backend.Controller;

import com.project.travello_backend.RequestEntity.MessageResponse;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class ChatController {
    @MessageMapping("/resume")
    @SendTo("/start/initial")
    public MessageResponse chat(String msg, @Header("login") String username) {
        MessageResponse message = new MessageResponse();
        message.setUsername(username);
        message.setMessage(msg);
        System.out.println("Message from user " + username + ": " + msg);
        System.out.println(msg);
        return message;
    }
}
