package app.adapters.rest;

import app.domain.models.User;
import app.domain.services.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private AdministratorService administratorService;

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody UserRequest request) {
        try {
            User user = new User();
            user.setDocument(request.getDocument());
            user.setName(request.getName());
            user.setAge(request.getAge());
            user.setUserName(request.getUserName());
            user.setPassword(request.getPassword());
            user.setRole(request.getRole());
            // Registrar según el rol
            if ("Veterinarian".equalsIgnoreCase(user.getRole())) {
                administratorService.createVeterinarian(user);
            } else if ("Seller".equalsIgnoreCase(user.getRole())) {
                administratorService.createSeller(user);
            } else {
                return ResponseEntity.badRequest().body("Rol no soportado");
            }
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            User user = administratorService.authenticateUser(request.getUserName(), request.getPassword());
            if (user == null) {
                return ResponseEntity.status(401).body("Credenciales incorrectas");
            }
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Error: " + e.getMessage());
        }
    }
}

// DTOs
class UserRequest {
    private long document;
    private String name;
    private int age;
    private String userName;
    private String password;
    private String role;

    // Getters y setters
    public long getDocument() { return document; }
    public void setDocument(long document) { this.document = document; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}

class LoginRequest {
    private String userName;
    private String password;

    // Getters y setters
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
