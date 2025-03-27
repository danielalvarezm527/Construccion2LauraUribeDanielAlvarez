package app.domain.services;


import app.domain.models.Person;
import app.domain.models.User;
import app.ports.PersonPort;
import app.ports.UserPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Setter
@Getter
@Service
@NoArgsConstructor
public class AdministratorService {
    @Autowired
    private PersonPort personPort;
    @Autowired
    private UserPort userPort;


    public void createVeterinarian(User personVeterinarian) throws Exception {
        Person person = personPort.findByPersonId(personVeterinarian.getDocument());
        if (person != null) {
            throw new Exception(" Ya existe una persona con esa cédula");
        }
        personVeterinarian.setRole("Veterinarian");
        personPort.save(personVeterinarian);
        userPort.save(personVeterinarian);

    }

    public void createSeller(User personSeller) throws Exception {
        Person person = personPort.findByPersonId(personSeller.getDocument());
        if (person != null) {
            throw new Exception(" Ya existe una persona con esa cédula");
        }
        personSeller.setRole("Seller");
        personPort.save(personSeller);
        userPort.save(personSeller);
    }
}