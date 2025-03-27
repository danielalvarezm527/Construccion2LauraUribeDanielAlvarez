package app.ports;

import app.domain.models.Pet;

public interface PetPort {
	Pet findByPetId(long petId);
	void save(Pet pet);
}

