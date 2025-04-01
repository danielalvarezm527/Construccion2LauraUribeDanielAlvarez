package app.adapters.pets;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.adapters.person.entity.PersonEntity;
import app.adapters.person.repository.PersonRepository;
import app.adapters.pets.entity.PetEntity;
import app.adapters.pets.repository.PetRepository;
import app.domain.models.Person;
import app.domain.models.Pet;
import app.ports.PetPort;

@Service
public class PetAdapter implements PetPort {

	@Autowired
    private PetRepository petRepository;
    
    @Autowired
    private PersonRepository personRepository;
    
    @Override
    public Pet findByPetId(long petId) {
        Optional<PetEntity> petEntityOptional = petRepository.findById(petId);
        if (petEntityOptional.isPresent()) {
            return mapToPet(petEntityOptional.get());
        }
        return null;
    }

    @Override
    public void save(Pet pet) {
        PetEntity entity = new PetEntity();
        entity.setPetId(pet.getPetId());
        entity.setName(pet.getName());
        entity.setAge(pet.getAge());
        entity.setSpecies(pet.getSpecies());
        entity.setRace(pet.getRace());
        entity.setWeight(pet.getWeight());
        entity.setCaracteristicas(pet.getCaracteristicas());
        
        if (pet.getOwner() != null) {
            Optional<PersonEntity> ownerOptional = personRepository.findById(pet.getOwner().getDocument());
            if (ownerOptional.isPresent()) {
                entity.setOwner(ownerOptional.get());
            }
        }
        
        petRepository.save(entity);
        
        pet.setPetId(entity.getPetId());
    }
    
    private Pet mapToPet(PetEntity entity) {
        Person owner = null;
        if (entity.getOwner() != null) {
            PersonEntity ownerEntity = entity.getOwner();
            owner = new Person(ownerEntity.getDocument(), ownerEntity.getName(), ownerEntity.getAge(), ownerEntity.getRole());
        }
        
        return new Pet(
            entity.getName(),
            owner,
            entity.getAge(),
            entity.getPetId(),
            entity.getSpecies(),
            entity.getRace(),
            entity.getWeight(),
            entity.getCaracteristicas()
        );
    }

}
