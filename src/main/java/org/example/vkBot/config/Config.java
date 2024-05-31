package org.example.vkBot.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "tkn")
@Getter
public class Config {
    private String token;
    private String confirmationToken;
}
