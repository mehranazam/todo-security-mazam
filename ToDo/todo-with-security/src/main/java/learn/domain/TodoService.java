package learn.domain;

import learn.data.TodoRepo;
import learn.data.UserRepo;
import learn.models.AppUser;
import learn.models.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class TodoService {
    @Autowired
    TodoRepo tRepo;

    @Autowired
    UserRepo uRepo;

    public List<Todo> getPublicTodos() {
        return tRepo.findAllPublic();
    }

    public void deleteById(Integer todoId, Principal user) throws InvalidUserException {
        Todo toDelete = tRepo.findById(todoId);
        AppUser requester = uRepo.findByUsername(user.getName());

        //TODO: make sure we didn't get back any nulls, check that todoId isn't null check that user isn't null etc

        if(requester.getRoles().contains("ADMIN")
                ||
                requester.getUserId().intValue() == toDelete.getUserId().intValue()){
            //this is an admin or the same person who wrote the todo
            tRepo.remove(todoId);
        } else {
            throw new InvalidUserException("Only admins and the author of the todo may delete it.");
        }
    }
}
