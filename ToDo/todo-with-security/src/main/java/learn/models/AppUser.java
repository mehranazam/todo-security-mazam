package learn.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class AppUser extends User {

    private Set<String> roles;
    private Integer userId;

    public AppUser(Integer userId, String username, String password, Set<String> roles){
        super(username, password, roles.stream().map(r -> new SimpleGrantedAuthority("ROLE_ " + r)).collect(Collectors.toList()));
        this.userId = userId;
        this.roles = roles;
    }

//    public AppUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
//        super(username, password, authorities);
//    }
//
//    public AppUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
//        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
//    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AppUser appUser = (AppUser) o;
        return userId == appUser.userId && Objects.equals(roles, appUser.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), roles, userId);
    }
}
