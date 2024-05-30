package org.example.vkBot.service;

import org.example.vkBot.model.Message;
import org.example.vkBot.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }
    public Optional<Message> find(String text) {
        return messageRepository.findMessageByContent(text);
    }
    public String get(Long id) {
        var message = messageRepository.findById(id).orElse(null);
        return "Вы сказали: " + (message != null ? message.getContent() : null);
    }
    public Message create(Message message) {
        return messageRepository.save(message);
    }
    public Message update(Message message, String text) {
        var updatedMessage = messageRepository.findMessageByContent(text).orElseThrow();
        updatedMessage.setContent(message.getContent());
        return messageRepository.save(updatedMessage);
    }
    public void delete(Long id) {
        messageRepository.deleteById(id);
    }
}
