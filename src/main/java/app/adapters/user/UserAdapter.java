package app.adapters.user;

import app.adapters.person.entity.PersonEntity;
import app.adapters.user.repository.UserRepository;
import app.adapters.user.entity.UserEntity;
import app.domain.models.Person;
import app.domain.models.User;
import app.ports.UserPort;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAdapter implements UserPort {
    
    @Override
    public User findByUserId(long userId) {
        return null;
    }

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        UserEntity userEntity =userEntityAdapter(user);
        userRepository.save(userEntity);
        user.setUserId(userEntity.getUserId());
    }

    private PersonEntity personEntityAdapter(Person person){
        PersonEntity personEntity = new PersonEntity();
        personEntity.setDocument(person.getDocument());
        personEntity.setName(person.getName());
        personEntity.setAge(person.getAge());
        personEntity.setRole(person.getRole());
        return personEntity;
    }

    private UserEntity userEntityAdapter(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(user.getUserId());
        userEntity.setUserName(user.getUserName());
        userEntity.setPassword(user.getPassword());
        return userEntity;
    }

    @Override
    public User findByUserId(long userId) {
        Optional<UserEntity> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            UserEntity entity = userOptional.get();
            User user = new User();
            user.setDocument(entity.getDocument());
            user.setName(entity.getName());
            user.setAge(entity.getAge());
            user.setRole(entity.getRole());
            user.setUserName(entity.getUserName());
            user.setPassword(entity.getPassword());
            user.setUserId(entity.getUserId());
            return user;
        }
        return null;
    }
}
