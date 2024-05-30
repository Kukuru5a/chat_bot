package org.example.vkBot.controller;

import org.example.vkBot.model.Message;
import org.example.vkBot.repository.MessageRepository;
import org.example.vkBot.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bot")
public class BotContorller {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    MessageService messageService;

    @GetMapping("/{id}")
    String getMessageById(@PathVariable Long id) {
        return messageService.get(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    Message createBot(@RequestBody Message message) {
        return messageService.create(message);
    }
}
