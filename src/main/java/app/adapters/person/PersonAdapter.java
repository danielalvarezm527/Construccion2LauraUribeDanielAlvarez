package app.adapters.person;

import app.adapters.person.entity.PersonEntity;
import app.adapters.person.repository.PersonRepository;
import app.domain.models.Person;
import app.ports.PersonPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Setter
@Getter
@NoArgsConstructor
@Service
public class PersonAdapter implements PersonPort {

	@Autowired
    private PersonRepository personRepository;

    @Override
    public Person findByPersonId(long document) {
        Optional<PersonEntity> personEntityOptional = personRepository.findById(document);
        if (personEntityOptional.isPresent()) {
            PersonEntity entity = personEntityOptional.get();
            return new Person(entity.getDocument(), entity.getName(), entity.getAge(), entity.getRole());
        }
        return null;
    }

    @Override
    public void save(Person person) {
        PersonEntity entity = new PersonEntity();
        entity.setDocument(person.getDocument());
        entity.setName(person.getName());
        entity.setAge(person.getAge());
        entity.setRole(person.getRole());
        
        personRepository.save(entity);
    }
}
