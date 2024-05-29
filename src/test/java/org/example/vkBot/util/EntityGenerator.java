package org.example.vkBot.util;

import net.datafaker.Faker;
import org.example.vkBot.model.Message;
import org.instancio.Instancio;
import org.instancio.Select;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;


@Component
public class EntityGenerator {
    Faker faker = new Faker();

    @Bean
    public Message generateMessage() {
        return Instancio.of(Message.class)
                .ignore(Select.field(Message::getId))
                .supply(Select.field(Message::getContent), () -> faker.text().text(3,300))
                .create();
    }
}
