package org.example.vkBot.util;

import org.example.vkBot.model.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class TextGenerator {
    @Bean
    public Message generateMessage() {
        var message = new Message();
        message.setId(1L);
        message.setContent("Hello World!");
        return message;
    }
}
