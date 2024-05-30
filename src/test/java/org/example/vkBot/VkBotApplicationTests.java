package org.example.vkBot;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.vkBot.service.BotService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class VkBotApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BotService botService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testHandleMessage() throws Exception {
		Map<String, Object> payload = new HashMap<>();
		payload.put("user_id", 123);
		payload.put("message", "Test message");

		mockMvc.perform(MockMvcRequestBuilders.post("/message")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(payload)))
				.andExpect(status().isOk());

		verify(botService).receiveMessage(123, "Test message");
	}

	@Test
	public void testHandleQuote() throws Exception {
		when(botService.quotTheMessage(123L)).thenReturn("Quoted message");

		Map<String, Object> payload = new HashMap<>();
		payload.put("user_id", 123);

		mockMvc.perform(MockMvcRequestBuilders.post("/quote")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(payload)))
				.andExpect(status().isOk());
	}

}
