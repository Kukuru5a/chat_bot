package org.example.vkBot.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.vkBot.model.Message;
import org.openapitools.jackson.nullable.JsonNullable;

@Getter
@Setter
public class MessageUpdateDTO {
    private JsonNullable<String> content;
}
