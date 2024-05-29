package org.example.vkBot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.vkBot.dto.MessageCreateDTO;
import org.example.vkBot.dto.MessageUpdateDTO;
import org.example.vkBot.model.Message;
import org.example.vkBot.repository.MessageRepository;
import org.example.vkBot.util.EntityGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class VkBotApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private EntityGenerator entityGenerator;
	@Autowired
	private ObjectMapper om;
	@Autowired
	private MessageRepository messageRepository;

	@BeforeEach
    public void setUp() {
		var message = entityGenerator.generateMessage();
		messageRepository.save(message);

	}

    @AfterEach
	public void clean() {
		messageRepository.deleteAll();
	}


	@Test
	void createTest() throws Exception {
		var message = entityGenerator.generateMessage();
//		var message = Message.builder()
//				.content("Расскажи как у тебя дела? Что нового? Почему ты мне не отвечаешь?")
//				.build();
		var data = new MessageCreateDTO();
		data.setContent(message.getContent());


		var request = post("/api/messages")
				.contentType(MediaType.APPLICATION_JSON)
				.content(om.writeValueAsString(data));
		mockMvc.perform(request)
				.andExpect(status().isCreated());
	}

	@Test
	void updateTest() throws Exception {
		var message = entityGenerator.generateMessage();
		var data = new MessageUpdateDTO();
		data.setContent(JsonNullable
				.of("new contnent for the text. It may look as far as I want it. Pickle Rick!)"));

	}

}
