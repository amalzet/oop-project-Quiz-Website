package ge.edu.freeuni.server.repository.user;

import ge.edu.freeuni.api.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private int idCounter = 1;


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public boolean addUserToDB(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        if (isUsernameUsed(username))
            return false;
        idCounter++;
        jdbcTemplate.execute("insert into user (id, username, password) values (" + idCounter + ","
                + "\"" + username + "\" , \"" + password + "\");");
        return true;
    }

    private boolean isUsernameUsed(String username) {
        String search = "select u.username from user u where u.username = \"" + username + "\";";
        List<String> users = new ArrayList<>();
        users.addAll(jdbcTemplate.queryForList(search, String.class));
        return users.size() != 0;
    }

    @Override
    public boolean isUserValid(User user) {
        List<String> users = new ArrayList<>();
        String username = user.getUsername();
        String password = user.getPassword();
        String search = "select u.username from user u where u.username = \"" + username + "\""
                + " and u.password = \"" + password + "\";";
        users.addAll(jdbcTemplate.queryForList(search, String.class));
        return users.size() == 1;
    }
}
