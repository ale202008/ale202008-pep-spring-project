package com.example.service;
import org.springframework.beans.factory.annotation.Autowired;
// Adding imports
import org.springframework.stereotype.Service;
import com.example.repository.MessageRepository;
import com.example.entity.Message;
import java.util.Optional;

@Service
public class MessageService {
    // Initialize MessageRepository field
    private MessageRepository messageRepository;

    // Implementing constructor for service to initalize AccountRepository
    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }
}
