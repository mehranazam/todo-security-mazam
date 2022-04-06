package learn.models;

import java.time.LocalDate;
import java.util.Objects;

public class Todo {

    private String text;
    private int userId;
    private boolean isPublic;
    private LocalDate createDate;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isPublic() {
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
        return userId == todo.userId && isPublic == todo.isPublic && Objects.equals(text, todo.text) && Objects.equals(createDate, todo.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, userId, isPublic, createDate);
    }
}
