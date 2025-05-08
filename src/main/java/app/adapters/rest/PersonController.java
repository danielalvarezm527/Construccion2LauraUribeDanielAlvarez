package app.adapters.rest;

import app.domain.models.Person;
import app.ports.PersonPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owners")
public class PersonController {

    @Autowired
    private PersonPort personPort;

    @PostMapping
    public ResponseEntity<?> createOwner(@RequestBody PersonRequest request) {
        try {
            Person person = new Person(request.getDocument(), request.getName(), request.getAge(), "Owner");
            personPort.save(person);
            return ResponseEntity.ok(person);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{document}")
    public ResponseEntity<?> getOwner(@PathVariable long document) {
        Person person = personPort.findByPersonId(document);
        if (person == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(person);
    }
}

// DTO para la petición
class PersonRequest {
    private long document;
    private String name;
    private int age;

    // Getters y setters
    public long getDocument() { return document; }
    public void setDocument(long document) { this.document = document; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}
