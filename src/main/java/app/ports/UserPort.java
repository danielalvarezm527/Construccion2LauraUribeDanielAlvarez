package app.ports;

import java.util.List;
import app.domain.models.User;

public interface UserPort {
    User findByUserId(long userId);
    void save(User user);
    User findByUserNameAndPassword(String userName, String password);
    List<User> findAll();
}
