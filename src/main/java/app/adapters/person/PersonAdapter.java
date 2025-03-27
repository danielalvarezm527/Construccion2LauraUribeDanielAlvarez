package app.adapters.person;

import app.domain.models.Person;
import app.ports.PersonPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Setter
@Getter
@NoArgsConstructor
@Service
public class PersonAdapter implements PersonPort {

    @Override
    public Person findByPersonId(long document) {
        Person person = new Person();
        return person;
    }

    @Override
    public void save(Person person) {

    }
}
