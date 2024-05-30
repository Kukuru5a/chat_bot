package org.example.vkBot.service;

import org.example.vkBot.model.Bot;
import org.example.vkBot.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BotService {
    @Autowired
    private MessageRepository repository; // ?

    private Map<Long, String> messages = new HashMap<>();

    public void receiveMessage(long userId, String message) {
        messages.put(userId, message);
    }

    public String quotTheMessage(Long userId) {
        String lastMessage = messages.get(userId);

        return lastMessage != null ? "Вы сказали: " + lastMessage : "Ранее Вы этого не говорили, запомню!";
    }
}
