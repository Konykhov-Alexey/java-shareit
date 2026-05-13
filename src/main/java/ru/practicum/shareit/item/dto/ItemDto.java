package ru.practicum.shareit.item.dto;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class ItemDto {

    private Long id;

    private String name;

    private String description;

    private Boolean available;
}
