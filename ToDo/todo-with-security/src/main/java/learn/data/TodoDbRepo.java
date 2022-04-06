package learn.data;

import learn.models.Todo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoDbRepo implements TodoRepo{


    @Override
    public List<Todo> findAllPublic() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Todo> findByUserId(Integer userId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Todo findById(Integer todoId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Todo add(Todo toAdd) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Integer todoId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void edit(Todo updated) {
        throw new UnsupportedOperationException();
    }
}
