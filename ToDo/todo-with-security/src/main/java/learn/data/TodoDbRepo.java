package learn.data;

import learn.models.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoDbRepo implements TodoRepo{

@Autowired
    JdbcTemplate template;

    @Override
    public List<Todo> findAllPublic() {
        String sql = "select * from todos where isPublic = 1;";
        return template.query(sql, new TodoMapper());
    }

    @Override
    public List<Todo> findByUserId(Integer userId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Todo findById(Integer todoId) {
        return template.query("select * from todos where todoId = ?", new TodoMapper(), todoId).stream().findAny().orElse(null);
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
