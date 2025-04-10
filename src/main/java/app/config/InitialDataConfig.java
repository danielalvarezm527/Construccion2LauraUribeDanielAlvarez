package app.config;

import app.domain.models.User;
import app.domain.models.Person;
import app.ports.UserPort;
import app.ports.PersonPort;
import jakarta.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
public class InitialDataConfig {

    @Autowired
    private UserPort userPort;
    
    @Autowired
    private PersonPort personPort;
    
    @PostConstruct
    public void initData() {
        long adminDocument = 123456789;
        
        if (userPort.findByUserId(adminDocument) == null) {
            Person adminPerson = new Person();
            adminPerson.setDocument(adminDocument);
            adminPerson.setName("Admin");
            adminPerson.setAge(30);
            adminPerson.setRole("Administrator");
            
            personPort.save(adminPerson);
            
            User admin = new User();
            admin.setDocument(adminDocument);
            admin.setName("Admin");
            admin.setAge(30);
            admin.setUserName("admin");
            admin.setPassword("admin");
            admin.setRole("Administrator");
            
            userPort.save(admin);
            
            System.out.println("Usuarios iniciales creados exitosamente");
        }
    }
}