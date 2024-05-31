package org.example.vkBot.controller;

import org.example.vkBot.service.BotService;
import org.example.vkBot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class BotController {
    @Autowired
    private final BotService service;

    @Autowired
    private final UserService userService;

    public BotController(BotService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @PostMapping("/message")
    ResponseEntity<String> handleService(@RequestBody Map<String, Long> payload) {
        long userId = payload.get("user_id");

        String message = String.valueOf(payload.get("message"));
        service.receiveMessage(userId, message);
        return ResponseEntity.ok().body("Message received");
    }



    @PostMapping("/quote")
    ResponseEntity<String> quotMessage(@RequestBody Map<String, Object> payload) {
        long userId = (int) payload.get("user_id");
        return ResponseEntity.ok().body(service.quotTheMessage(userId));
    }

}
