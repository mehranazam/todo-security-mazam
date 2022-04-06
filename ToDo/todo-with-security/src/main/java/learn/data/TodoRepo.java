package learn.data;

import learn.models.Todo;

import java.util.List;

public interface TodoRepo {
    List<Todo> findAllPublic();

    List<Todo> findByUserId(Integer userId);

    Todo findById(Integer todoId);

    Todo add(Todo toAdd);

    boolean remove(Integer todoId);

    void edit (Todo updated);
}
