package app.adapters.orders.entity;

import java.sql.Timestamp;

import app.adapters.person.entity.PersonEntity;
import app.adapters.pets.entity.PetEntity;
import app.adapters.user.entity.UserEntity;
import app.domain.models.Person;
import app.domain.models.Pet;
import app.domain.models.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Orders")
@Getter
@Setter
@NoArgsConstructor
public class OrderEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Make sure this is IDENTITY
    @Column(name = "order_id")
    private Long orderId;
    
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private PetEntity pet;
    
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private PersonEntity owner;
    
    @ManyToOne
    @JoinColumn(name = "veterinarian_id")
    private UserEntity veterinarian;
    
    private String medicine;
    private String dose;
    private Timestamp date;
    private boolean cancelled;
}
