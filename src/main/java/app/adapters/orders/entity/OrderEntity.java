package app.adapters.orders.entity;

import java.sql.Timestamp;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;
    
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
