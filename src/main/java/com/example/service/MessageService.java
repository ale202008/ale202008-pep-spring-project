package com.example.service;
import org.springframework.beans.factory.annotation.Autowired;
// Adding imports
import org.springframework.transaction.annotation.*;
import org.springframework.stereotype.Service;
import com.example.repository.MessageRepository;
import com.example.entity.Message;
import java.util.Optional;
import java.util.List;


@Service
public class MessageService {
    // Initialize MessageRepository field
    private MessageRepository messageRepository;


    // Implementing constructor for service to initalize AccountRepository
    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }


    // Implemented method to save new Message in messageRepository
    @Transactional
    public Message createMessage(Message message){
        return messageRepository.save(message);
    }

    
    // Implemented method to get all messages
    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    
    // Implemented method to get message by id
    public Message getMessageByid(int id){
        return messageRepository.findById(id).orElse(null);
    }
}
