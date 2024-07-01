package net.javagudies.todo.Service.impl;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.javagudies.todo.Dto.TodoDto;
import net.javagudies.todo.Entity.Todo;

import net.javagudies.todo.Exception.NotFoundException;
import net.javagudies.todo.Exception.NotFoundException;
import net.javagudies.todo.Exception.TitleException;
import net.javagudies.todo.Repositary.TodoRepo;
import net.javagudies.todo.Service.TodoInter;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoImpl implements TodoInter {

    TodoRepo repo;
    ModelMapper mp;



    public TodoDto addTodo(TodoDto todoDto){
           Optional<Todo> opUser=repo.findBytitle(todoDto.getTitle());
           if(opUser.isPresent()){
               throw new TitleException("Title already exist");

           }
       Todo to= mp.map(todoDto,Todo.class);
        Todo yo =repo.save(to);
       TodoDto bo=  mp.map(yo, TodoDto.class);
        return bo;
    }

    @Override
    public TodoDto find(Long id) {
       Todo found= repo.findById(id).orElseThrow(
               ()->new NotFoundException("user","id",id)
       );
       TodoDto some=mp.map(found, TodoDto.class);
       return some;
    }

    public List<TodoDto> findAll(){
        List<Todo> allTodo=repo.findAll();
        return allTodo.stream().map((yo)->(
            mp.map(yo,TodoDto.class)
        )).collect(Collectors.toList());
    }

    @Override
    public TodoDto upDateTodo(TodoDto todoDto) {
        // first find the id
        Todo to=repo.findById(todoDto.getId()).orElseThrow(()->new NotFoundException("user","id", todoDto.getId()));
        to.setCompleted(to.getCompleted());
        to.setDescrption(to.getDescrption());
        to.setTitle(to.getTitle());
        Todo saveTodo=repo.save(to);
        return mp.map(saveTodo,TodoDto.class);

    }

    public void delTodoBy(Long id){

        Todo del=repo.findById(id).orElseThrow(()->new NotFoundException("User","id",id));
        repo.deleteById(id);
    }
  public TodoDto completeTodo(Long id){
        Todo exis=repo.findById(id).orElseThrow(()->new NotFoundException("User","id",id));
        exis.setCompleted(Boolean.TRUE);
        Todo com=repo.save(exis);
        return mp.map(com, TodoDto.class);

    }
   public TodoDto inComplete(Long id){
        Todo exe= repo.findById(id).orElseThrow(()-> new NotFoundException("User","id",id));
        exe.setCompleted(Boolean.FALSE);
         Todo up= repo.save(exe);
          return mp.map(up,TodoDto.class);
}

}