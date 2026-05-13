package ru.practicum.shareit.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleDuplicateEmail(EmailException ex) {
        return error(ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(NotFoundException ex) {
        return error(ex.getMessage());
    }

    @ExceptionHandler(DeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleAccessDenied(DeniedException ex) {
        return error(ex.getMessage());
    }

    @ExceptionHandler({ItemNotAvailableException.class,
            ItemOwnerBookingException.class,
            BookingNotCompletedException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequest(Exception ex) {
        return error(ex.getMessage());
    }

    private ErrorResponse error(String message) {
        log.error(message);
        return new ErrorResponse(message);
    }
}
