package net.javagudies.todo.Controller;

import  lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.javagudies.todo.Dto.TodoDto;
import net.javagudies.todo.Exception.ErrorDetails;
import net.javagudies.todo.Exception.NotFoundException;
import net.javagudies.todo.Service.TodoInter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("todo")


public class TodoController {

    TodoInter ti;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("create")
    public ResponseEntity<TodoDto> createTodo(@RequestBody TodoDto todoDto) {
        TodoDto rec = ti.addTodo(todoDto);
        return new ResponseEntity<>(rec, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("{id}")
    public ResponseEntity<TodoDto> goFind(@PathVariable Long id) {
        TodoDto findID = ti.find(id);
        return new ResponseEntity<>(findID, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("getall")
    public ResponseEntity<List<TodoDto>> getAllTodo() {
        List<TodoDto> yo = ti.findAll();
        return new ResponseEntity<>(yo, HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("update/{id}")
    public ResponseEntity<TodoDto> upDateTodo(@PathVariable Long id, @RequestBody TodoDto todoDto) {
        todoDto.setId(id);
        TodoDto up = ti.upDateTodo(todoDto);
        return new ResponseEntity<>(up, HttpStatus.CREATED);

    }

    @PreAuthorize("hasRole(ADMIN)")
    @DeleteMapping("del/{id}")
    public String delTodo(@PathVariable Long id) {
        ti.delTodoBy(id);
        return "Todo id deleted:" + id;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("status/{id}")
    public ResponseEntity<TodoDto> updateComplete(@PathVariable Long id) {
        TodoDto comp = ti.completeTodo(id);
        return new ResponseEntity<>(comp, HttpStatus.CREATED);

    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("mark/{id}")
    public ResponseEntity<TodoDto> mark(@PathVariable Long id) {
        TodoDto iscom = ti.inComplete(id);
        return new ResponseEntity<>(iscom, HttpStatus.CREATED);
    }




}
