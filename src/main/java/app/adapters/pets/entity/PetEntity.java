package app.adapters.pets.entity;

import app.adapters.person.entity.PersonEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="pets")
@Setter
@Getter
@NoArgsConstructor
public class PetEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long petId;
    
    private String name;
    private int age;
    private String species;
    private String race;
    private double weight;
    private String caracteristicas;
    
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private PersonEntity owner;
}
