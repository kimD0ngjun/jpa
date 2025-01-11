package com.example.jpa.persistenceContext.idea.controller;

import com.example.jpa.persistenceContext.idea.entity.Message;
import com.example.jpa.persistenceContext.idea.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping("/message")
    public String getMessage() {
        Message message = messageService.findById(1L);
        String hashcode = "객체 해시코드 : " + message.hashCode();
        String content = "객체 메세지 : " + message.getMessage();

        return hashcode + "\n" + content;
    }
}
