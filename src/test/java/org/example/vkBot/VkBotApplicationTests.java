package org.example.vkBot;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.vkBot.model.Message;
import org.example.vkBot.repository.MessageRepository;
import org.example.vkBot.util.TextGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.JwtRequestPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class VkBotApplicationTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TextGenerator textGenerator;
    @Autowired
    private MessageRepository messageRepository;

    private JwtRequestPostProcessor jwtRequestPostProcessor;

    private Message message;

    @BeforeEach
    void setUp() {
        message = textGenerator.generateMessage();
        messageRepository.save(message);
    }

    @AfterEach
    void tearDown() {
        messageRepository.deleteAll();
    }

    @Test
    void createMessage() throws Exception {
        var message = textGenerator.generateMessage();
        var request = post("/bot")
                .with(jwtRequestPostProcessor)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(message));
        mockMvc.perform(request)
                .andExpect(status().isCreated());
    }

    @Test
    void getMessage() throws Exception {
        var request = get("/bot/" + message.getId());
//                .with(jwtRequestPostProcessor);
        var response = mockMvc.perform(request).andExpect(status().isOk()).andReturn();
        var body = response.getResponse().getContentAsString();
        assertThatJson(body).and(
                a -> a.node("id").isEqualTo(message.getId()),
                a -> a.node("content").isEqualTo(message.getContent())
        );
    }

}
