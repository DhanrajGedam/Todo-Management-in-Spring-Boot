package net.javagudies.todo.Exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@Getter
@Setter
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class TitleException extends  RuntimeException {
    String message;

    public TitleException(String message) {
        super(message);

    }
}
