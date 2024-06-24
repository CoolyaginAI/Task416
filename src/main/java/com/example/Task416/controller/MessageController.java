package com.example.Task416.controller;

import com.example.Task416.dto.Message;
import com.example.Task416.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;


@RestController
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/message")
    public Iterable<Message> getMessage() {
        return messageRepository.findAll();
    }

    @GetMapping("/message/{id}")
    public Optional<Message> findMessageById(@PathVariable int id) {
        return messageRepository.findById(id);
    }

    @PostMapping("/message")
    public Message addMessage(@RequestBody Message message) {
        messageRepository.save(message);
        return message;
    }

    @PutMapping("/message/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable int id, @RequestBody Message message) {

        HttpStatus status = messageRepository.existsById(id) ? HttpStatus.OK : HttpStatus.CREATED;

        messageRepository.findById(id).get().setTitle(message.getTitle());
        messageRepository.findById(id).get().setText(message.getText());
        messageRepository.findById(id).get().setTime(message.getTime());

        return  new ResponseEntity(messageRepository.save(messageRepository.findById(id).get()), status);

    }

    @DeleteMapping("/message/{id}")
    public void deleteMessage(@PathVariable int id) {
        messageRepository.deleteById(id);
    }

}
