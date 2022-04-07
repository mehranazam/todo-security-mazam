package learn.data;

import learn.models.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class UserDbRepo implements UserRepo{

    @Autowired
    JdbcTemplate jdbcTemplate;

    private Set<String> findRolesByUsername(String username){
        String sql = "select roleName from users u inner join userroles ur on ur.userId = u.userId inner join roles r on r.roleId = ur.roleId where username = ?";
        return jdbcTemplate.query(sql, (rowData, rowNum) -> rowData.getString("roleName"), username).stream().collect(Collectors.toSet());
    }



    @Override
    public AppUser findByUsername(String username) {
        String sql = "select userId, username, password from users where username = ?";
        return jdbcTemplate.query(
                sql, new UserMapper(findRolesByUsername(username)), username).stream().findAny().orElse(null);
    }

    @Override
    public AppUser add(AppUser toAdd) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Integer userId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void edit(AppUser updated) {
        throw new UnsupportedOperationException();
    }
}
