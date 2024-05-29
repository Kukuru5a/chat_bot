package org.example.vkBot.controller;

import org.example.vkBot.dto.MessageCreateDTO;
import org.example.vkBot.dto.MessageDTO;
import org.example.vkBot.dto.MessageUpdateDTO;
import org.example.vkBot.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    MessageDTO createMessage(@RequestBody MessageCreateDTO messageDTO) {
        return messageService.createMessage(messageDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    MessageDTO updateMessage(@RequestBody MessageUpdateDTO messageUpdateDTO, @PathVariable Long id) {
        return messageService.update(messageUpdateDTO, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
    }
}
