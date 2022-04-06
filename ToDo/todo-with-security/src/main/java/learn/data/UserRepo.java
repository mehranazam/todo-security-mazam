package learn.data;

import learn.models.AppUser;
import org.springframework.security.core.userdetails.User;

public interface UserRepo {
    AppUser findByUsername(String username);

    AppUser add(AppUser toAdd);

    boolean remove(Integer userId);

    void edit(AppUser updated);

}
