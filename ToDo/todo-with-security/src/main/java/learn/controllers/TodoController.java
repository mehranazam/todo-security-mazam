package learn.controllers;

import learn.domain.InvalidUserException;
import learn.domain.TodoService;
import learn.models.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController {

    @Autowired
    TodoService service;

    @GetMapping("/public")
    public ResponseEntity getPublicTodos(){
        List<Todo> pubTodos = service.getPublicTodos();
        return ResponseEntity.ok(pubTodos);
    }


    @DeleteMapping("/{todoId}")
    public ResponseEntity deleteById(@PathVariable Integer todoId, Principal user){
        try {
            service.deleteById(todoId, user);
            return ResponseEntity.ok().build();
        } catch (InvalidUserException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.FORBIDDEN);
        }

    }




}
