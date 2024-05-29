package org.example.vkBot.mapper;

import org.example.vkBot.dto.MessageCreateDTO;
import org.example.vkBot.dto.MessageDTO;
import org.example.vkBot.dto.MessageUpdateDTO;
import org.example.vkBot.model.Message;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        uses = {JsonNullableMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class MessageMapper {
    public abstract Message map(MessageCreateDTO dto);
    public abstract MessageDTO map(Message model);
    public abstract void update(MessageUpdateDTO dto, @MappingTarget Message model);

}
