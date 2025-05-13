package app.adapters.rest;

import app.adapters.rest.dto.LoginRequest;
import app.adapters.rest.dto.UserRequest;
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
