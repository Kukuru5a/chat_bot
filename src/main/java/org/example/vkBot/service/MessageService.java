package org.example.vkBot.service;

import org.example.vkBot.dto.MessageCreateDTO;
import org.example.vkBot.dto.MessageDTO;
import org.example.vkBot.dto.MessageUpdateDTO;
import org.example.vkBot.mapper.MessageMapper;
import org.example.vkBot.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private MessageMapper messageMapper;

    public MessageDTO createMessage(MessageCreateDTO messageDTO) {
        var message = messageMapper.map(messageDTO);
        messageRepository.save(message);
        return messageMapper.map(message);
    }

    public MessageDTO update(MessageUpdateDTO messageUpdateDTO, Long id) {
        var message = messageRepository.findById(id).orElseThrow();
        messageMapper.update(messageUpdateDTO, message);
        messageRepository.save(message);
        return messageMapper.map(message);
    }

    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }
}
