package app.adapters.rest;

import app.adapters.rest.dto.PetRequest;
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
