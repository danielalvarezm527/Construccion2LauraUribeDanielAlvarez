package app.adapters.rest;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import app.adapters.rest.request.UserRequest;

@RestController
public class AdminController {
    
    @GetMapping("/")
    public String getAdmin() {
        return "Admin page";
    }

    @PostMapping("/user")
    public String createAdmin(@Validated UserRequest userRequest) {
        return "Exito";
    }
    
}
