package org.example.vkBot.controller;

import org.example.vkBot.config.Config;
import org.example.vkBot.service.BotService;
import org.example.vkBot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

import static org.example.vkBot.config.Config.CONFIRMATION_TOKEN;

@RestController
@RequestMapping
public class BotController {
    @Autowired
    BotService service;

    @Autowired
    UserService userService;

    @PostMapping("/message")
    String handleService(@RequestBody Map<String, Object> payload) {
        long userId = (int) payload.get("user_id");

        String message = (String) payload.get("message");
        service.receiveMessage(userId, message);
        return "Message received";
    }


    @PostMapping("/")
    public String handleCallback(@RequestBody Map<String, Object> payload) throws IOException {
        // Проверяем, есть ли поле "type"
        if (payload.containsKey("type")) {
            String type = (String) payload.get("type");

            // Если это уведомление для подтверждения сервера
            if ("confirmation".equals(type)) {
                return CONFIRMATION_TOKEN;
            }

            // Если это новое сообщение
            else if ("message_new".equals(type)) {
                Map<String, Object> object = (Map<String, Object>) payload.get("object");
                Integer userId = (Integer) object.get("from_id");
                String userName = userService.getUserName(userId);

                // Отправляем ответное сообщение
                String message = String.format("Hello, %s!", userName);
                userService.sendMessage(userId, message);

                // Возвращаем "ok" серверу Callback API
                return "ok";
            }
        }
        return "ok";
    }

    @PostMapping("/quote")
    String quotMessage(@RequestBody Map<String, Object> payload) {
        long userId = (int) payload.get("user_id");
        return service.quotTheMessage(userId);
    }

}
