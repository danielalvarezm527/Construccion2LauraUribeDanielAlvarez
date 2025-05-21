package app.adapters.user;

import app.adapters.person.entity.PersonEntity;
import app.adapters.person.repository.PersonRepository;
import app.adapters.user.repository.UserRepository;
import app.adapters.user.entity.UserEntity;
import app.domain.models.Person;
import app.domain.models.User;
import app.ports.UserPort;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAdapter implements UserPort {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PersonRepository personRepository;

    @Override
    public void save(User user) {
        UserEntity userEntity = userEntityAdapter(user);
        userRepository.save(userEntity);
        user.setUserId(userEntity.getUserId());
    }

    private PersonEntity personEntityAdapter(Person person){
        // Check if person already exists
        Optional<PersonEntity> existingPerson = personRepository.findById(person.getDocument());
        if (existingPerson.isPresent()) {
            return existingPerson.get();
        }
        
        // If not, create a new one
        PersonEntity personEntity = new PersonEntity();
        personEntity.setDocument(person.getDocument());
        personEntity.setName(person.getName());
        personEntity.setAge(person.getAge());
        personEntity.setRole(person.getRole());
        return personEntity;
    }

    private UserEntity userEntityAdapter(User user) {
        UserEntity userEntity = new UserEntity();
        
        // Only set ID if it's an existing user with a valid ID
        if (user.getUserId() > 0) {
            userEntity.setUserId(user.getUserId());
        }
        
        userEntity.setUserName(user.getUserName());
        userEntity.setPassword(user.getPassword());
        
        // First check if this person already exists in the database
        PersonEntity personEntity = null;
        if (user.getDocument() > 0) {
            Optional<PersonEntity> existingPerson = personRepository.findById(user.getDocument());
            if (existingPerson.isPresent()) {
                personEntity = existingPerson.get();
            } else {
                // Create and possibly save a new person entity
                personEntity = new PersonEntity();
                personEntity.setDocument(user.getDocument());
                personEntity.setName(user.getName());
                personEntity.setAge(user.getAge());
                personEntity.setRole(user.getRole());
                
                // You might need to save the person entity first
                personRepository.save(personEntity);
            }
        }
        
        userEntity.setPerson(personEntity);
        return userEntity;
    }

    @Override
    public User findByUserNameAndPassword(String userName, String password) {
        List<UserEntity> allUsers = userRepository.findAll();
        
        for (UserEntity entity : allUsers) {
            if (entity.getUserName().equals(userName) && entity.getPassword().equals(password)) {
                User user = new User();
                user.setUserId(entity.getUserId());
                user.setUserName(entity.getUserName());
                user.setPassword(entity.getPassword());
                
                // Verificar que la persona no sea nula antes de acceder a sus propiedades
                if (entity.getPerson() != null) {
                    PersonEntity personEntity = entity.getPerson();
                    user.setDocument(personEntity.getDocument());
                    user.setName(personEntity.getName());
                    user.setAge(personEntity.getAge());
                    user.setRole(personEntity.getRole());
                }
                return user;
            }
        }
        
        return null;
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

    @Override
    public List<User> findAll() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<User> users = new ArrayList<>();
        
        for (UserEntity entity : userEntities) {
            User user = new User();
            user.setUserId(entity.getUserId());
            user.setUserName(entity.getUserName());
            user.setPassword(entity.getPassword());
            
            // Verificar que la persona no sea nula antes de acceder a sus propiedades
            if (entity.getPerson() != null) {
                PersonEntity personEntity = entity.getPerson();
                user.setDocument(personEntity.getDocument());
                user.setName(personEntity.getName());
                user.setAge(personEntity.getAge());
                user.setRole(personEntity.getRole());
            }
            users.add(user);
        }
        
        return users;
    }
}
