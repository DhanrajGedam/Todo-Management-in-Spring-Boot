package net.javagudies.todo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class Handler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDetails> handlError(NotFoundException exception, WebRequest webRequest) {
        ErrorDetails er = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "USER NOT FOUND"

        );
        return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(TitleException.class)
    public ResponseEntity<ErrorDetails> handlTitle(TitleException exception, WebRequest webRequest) {
        ErrorDetails er = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "Title Already exist"

        );
        return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);

    }


}
