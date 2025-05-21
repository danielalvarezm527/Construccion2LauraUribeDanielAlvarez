package app.config;

import app.domain.models.User;
import app.domain.models.Person;
import app.ports.UserPort;
import app.ports.PersonPort;
import jakarta.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Configuration
public class InitialDataConfig {

    @Autowired
    private UserPort userPort;
    
    @Autowired
    private PersonPort personPort;
    
    @PostConstruct
    public void initData() {
        try {
            long adminDocument = 123456789;
            
            // Verificar si ya existe un usuario con el nombre "admin"
            boolean adminExists = false;
            List<User> allUsers = userPort.findAll();
            
            for (User user : allUsers) {
                if ("admin".equals(user.getUserName())) {
                    adminExists = true;
                    System.out.println("El usuario administrador ya existe en el sistema.");
                    break;
                }
            }
            
            if (!adminExists) {
                // Verificar si ya existe la persona con ese documento
                Person existingPerson = personPort.findByPersonId(adminDocument);
                
                if (existingPerson == null) {
                    Person adminPerson = new Person();
                    adminPerson.setDocument(adminDocument);
                    adminPerson.setName("Admin");
                    adminPerson.setAge(30);
                    adminPerson.setRole("Administrator");
                    
                    personPort.save(adminPerson);
                } else {
                    System.out.println("Ya existe una persona con el documento " + adminDocument);
                }
                
                User admin = new User();
                admin.setDocument(adminDocument);
                admin.setName("Admin");
                admin.setAge(30);
                admin.setUserName("admin");
                admin.setPassword("admin");
                admin.setRole("Administrator");
                
                userPort.save(admin);
                
                System.out.println("Usuario administrador inicial creado exitosamente");
            }
        } catch (Exception e) {
            // Capturar cualquier excepción para evitar que la aplicación falle al iniciar
            System.err.println("Error al crear el usuario administrador inicial: " + e.getMessage());
            e.printStackTrace();
        }
    }
}