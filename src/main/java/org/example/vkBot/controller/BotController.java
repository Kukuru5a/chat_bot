package org.example.vkBot.controller;

import org.example.vkBot.service.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping
public class BotController {
    @Autowired
    BotService service;

    @PostMapping("/message")
    String handleService(@RequestBody Map<String, Object> payload) {
        long userId = (int) payload.get("user_id");

        String message = (String) payload.get("message");
        service.receiveMessage(userId, message);
        return "Message received";
    }

    @PostMapping("/quote")
    String quotMessage(@RequestBody Map<String, Object> payload) {
        long userId = (int) payload.get("user_id");
        return service.quotTheMessage(userId);
    }

}
