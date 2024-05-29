package org.example.vkBot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class VkBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(VkBotApplication.class, args);
	}

}
