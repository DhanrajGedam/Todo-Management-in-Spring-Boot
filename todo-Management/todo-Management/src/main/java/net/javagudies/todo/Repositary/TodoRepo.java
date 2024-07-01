package net.javagudies.todo.Repositary;

import net.javagudies.todo.Dto.TodoDto;
import net.javagudies.todo.Entity.Todo;
import net.javagudies.todo.Entity.TodoUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoRepo extends JpaRepository<Todo,Long> {
    Optional<Todo> findBytitle(String title);


}
