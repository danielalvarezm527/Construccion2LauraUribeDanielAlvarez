package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Pet{
	private String name;
	private Person owner;
	private int age;
	private long PetId;
	private String species;
	private String race;
	private double weight;
	private String caracteristicas;

	public Pet(String name, Person owner, int age, long petId, String species, String race, double weight, String caracteristicas) {
		this.name = name;
		this.owner= owner;
		this.age = age;
		PetId = petId;
		this.species = species;
		this.race = race;
		this.weight = weight;
		this.caracteristicas = caracteristicas;
	}
}



