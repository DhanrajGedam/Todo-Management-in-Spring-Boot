package net.javagudies.todo.Repositary;

import net.javagudies.todo.Entity.TodoUser;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<TodoUser,Long> {
        Optional<TodoUser> findByUsername(String username);

        Boolean existsByEmail(String email);

        Optional<TodoUser> findByUsernameOrEmail(String username, String email);




}
