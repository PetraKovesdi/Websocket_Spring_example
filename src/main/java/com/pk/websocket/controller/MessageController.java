package com.pk.websocket.controller;


import com.pk.websocket.model.MessageModel;
import com.pk.websocket.storage.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class MessageController {

    @Autowired
    private SimpMessagingTemplate simpleMessagingTemplate;

    @MessageMapping("/chat/{to}")
    public void sendMessage(@DestinationVariable String to, MessageModel message){
        System.out.println("Handle sent message is " + message + " to " + to);
        boolean isExists = UserStorage.getInstance().getUsers().contains(to);
        if (isExists){
            simpleMessagingTemplate.convertAndSend("topic/messages/" + to, message);
        }
    }

}
