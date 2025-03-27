package app.ports;

import app.domain.models.Person;
import app.domain.models.User;

public interface UserPort {
    User findByUserId(long userId);
    void save(User user);
}
