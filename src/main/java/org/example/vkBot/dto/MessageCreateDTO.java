package org.example.vkBot.dto;

import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
public class MessageCreateDTO {
    @NotNull
    private String content;
}
