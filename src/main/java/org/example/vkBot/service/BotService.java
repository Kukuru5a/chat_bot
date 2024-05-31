package org.example.vkBot.service;

import org.example.vkBot.model.Bot;
import org.example.vkBot.model.Message;
import org.example.vkBot.repository.MessageRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class BotService {
    @Autowired
    private MessageRepository repository;

    public final Map<Long, String> messages = new HashMap<>();

    public void receiveMessage(long userId, String message) {
        String mes;
        if (repository.findByContent(message).isPresent()) {
            mes = message;
        } else {
            mes = message;
        }
        messages.put(userId, mes);
    }


    public String quotTheMessage(Long userId) {
        String lastMessage = messages.get(userId);

        return lastMessage != null ? "Вы сказали: " + lastMessage : "Ранее Вы этого не говорили, запомню!";
    }
}
