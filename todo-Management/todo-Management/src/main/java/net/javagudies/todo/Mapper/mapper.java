package net.javagudies.todo.Mapper;

import net.javagudies.todo.Dto.TodoDto;
import net.javagudies.todo.Entity.Todo;

public interface mapper {
    Todo convertJpaToDto(TodoDto tododto);

    TodoDto convertDtoToJpa(Todo todo);


}
