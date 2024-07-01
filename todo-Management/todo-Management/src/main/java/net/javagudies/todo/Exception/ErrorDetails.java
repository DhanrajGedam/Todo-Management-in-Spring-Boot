package net.javagudies.todo.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter

@NoArgsConstructor
public class ErrorDetails {
    private LocalDateTime timestamp;
    private String message;
    private String path;
    private String errorcode;



    public ErrorDetails(LocalDateTime timestamp, String message, String path, String errorcode) {
        this.timestamp = timestamp;
        this.message=message;
        this.path=path;
        this.errorcode=errorcode;






    }
}
