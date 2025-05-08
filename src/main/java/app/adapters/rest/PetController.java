package app.adapters.rest;

import app.domain.models.Pet;
import app.domain.models.Person;
import app.ports.PetPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetPort petPort;

    @PostMapping
    public ResponseEntity<?> createPet(@RequestBody PetRequest request) {
        try {
            Person owner = new Person();
            owner.setDocument(request.getOwnerDocument());
            Pet pet = new Pet();
            pet.setName(request.getName());
            pet.setOwner(owner);
            pet.setAge(request.getAge());
            pet.setSpecies(request.getSpecies());
            pet.setRace(request.getRace());
            pet.setWeight(request.getWeight());
            pet.setCaracteristicas(request.getCaracteristicas());
            petPort.save(pet);
            return ResponseEntity.ok(pet);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPet(@PathVariable long id) {
        Pet pet = petPort.findByPetId(id);
        if (pet == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pet);
    }
}

// DTO para la petición
class PetRequest {
    private String name;
    private long ownerDocument;
    private int age;
    private String species;
    private String race;
    private double weight;
    private String caracteristicas;

    // Getters y setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public long getOwnerDocument() { return ownerDocument; }
    public void setOwnerDocument(long ownerDocument) { this.ownerDocument = ownerDocument; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }
    public String getRace() { return race; }
    public void setRace(String race) { this.race = race; }
    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }
    public String getCaracteristicas() { return caracteristicas; }
    public void setCaracteristicas(String caracteristicas) { this.caracteristicas = caracteristicas; }
}
