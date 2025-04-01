package app.ports;

import app.domain.models.Person;

/**
 *
 * @author ESTUDIANTE
 */
public interface PersonPort {

    public Person findByPersonId(long document);
    public void save(Person person);
}

