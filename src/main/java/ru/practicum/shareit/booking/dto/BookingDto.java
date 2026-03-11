package ru.practicum.shareit.booking.dto;

import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.enums.Status;

import java.time.LocalDateTime;

@Data
@Builder
public class BookingDto {

    private long id;

    private LocalDateTime start;

    private LocalDateTime end;

    private BookingItemDto item;

    private BookingUserDto booker;

    private Status status;
}
