package org.example.vkBot.service;

import org.example.vkBot.model.Bot;
import org.example.vkBot.model.Message;
import org.example.vkBot.repository.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BotServiceTest {

    @Mock
    private MessageRepository repository;

    @InjectMocks
    private BotService botService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testReceiveMessage() {
        when(repository.findByContent("Some message"))
                .thenReturn(Optional.of(new Message()));

        botService.receiveMessage(123L, "Test message");

        assertEquals("Test message", botService.messages.get(123L));
    }

    @Test
    public void testQuotTheMessage() {
        botService.messages.put(123L, "Test message");

        String quotedMessage = botService.quotTheMessage(123L);

        assertEquals("Вы сказали: Test message", quotedMessage);
    }
}
