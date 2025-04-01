package app.config;

import app.domain.models.User;
import app.ports.UserPort;
import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitialDataConfig {
	@Autowired
    private UserPort userPort;
    
    @PostConstruct
    public void initData() {
        if (userPort.findByUserId(123456789) == null) {
        	
            User admin = new User(123456789, "Admin", 30, "admin", "admin", "Administrator");
            userPort.save(admin);
            
            User vet = new User(987654321, "Veterinario", 35, "vet", "vet", "Veterinarian");
            userPort.save(vet);
            
            User seller = new User(456789123, "Vendedor", 25, "seller", "seller", "Seller");
            userPort.save(seller);
            
            System.out.println("Usuarios iniciales creados exitosamente");
        }
    }
}
