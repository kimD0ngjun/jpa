package com.example.jpa.persistenceContext.idea.service;

import com.example.jpa.persistenceContext.idea.entity.Message;
import com.example.jpa.persistenceContext.idea.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    @Transactional(readOnly = true)
    public Message findById(Long id){
        return messageRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }
}
