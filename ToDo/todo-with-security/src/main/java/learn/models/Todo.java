package learn.models;

import java.time.LocalDate;
import java.util.Objects;

public class Todo {

    private Integer todoId;
    private String text;
    private Integer userId;
    private Boolean isPublic;
    private LocalDate createDate;


    public Integer getTodoId() {
        return todoId;
    }

    public void setTodoId(Integer todoId) {
        this.todoId = todoId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return Objects.equals(todoId, todo.todoId) && Objects.equals(text, todo.text) && Objects.equals(userId, todo.userId) && Objects.equals(isPublic, todo.isPublic) && Objects.equals(createDate, todo.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(todoId, text, userId, isPublic, createDate);
    }
}
