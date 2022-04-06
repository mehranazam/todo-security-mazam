package learn.domain;

import learn.data.UserDbRepo;
import learn.data.UserRepo;
import learn.models.AppUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {


   // private String password;
    private PasswordEncoder encoder;
    UserRepo repo;



    public UserService(UserRepo repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser matchingUser = repo.findByUsername(username);

        //TODO: validate username is not null

        if(matchingUser == null){
            throw new UsernameNotFoundException(username + " not found.");
        }
        return matchingUser;
    }

    public AppUser create(String username, String password){
        //TODO: use encoder to hash password
        return null;
    }
}
