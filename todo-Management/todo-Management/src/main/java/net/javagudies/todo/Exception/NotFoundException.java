package net.javagudies.todo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{
    private  String resourceName;
    private  Long fieldValue;
    private  String fieldName;

    public NotFoundException(String resourceName,String fieldName,Long fieldValue) {
        super(String.format("Not found"));
        this.resourceName = resourceName;
        this.fieldName=fieldName;
        this.fieldValue=fieldValue;
    }
}
