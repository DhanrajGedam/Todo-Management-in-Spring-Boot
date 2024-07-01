package net.javagudies.todo.Service;

import net.javagudies.todo.Dto.TodoDto;

import java.util.List;


public interface TodoInter {

    TodoDto addTodo(TodoDto todo);

    TodoDto find(Long id);

    List<TodoDto> findAll();

    TodoDto upDateTodo(TodoDto todoDto);

    void delTodoBy(Long id);

    TodoDto completeTodo(Long id);

    TodoDto inComplete(Long id);


}
