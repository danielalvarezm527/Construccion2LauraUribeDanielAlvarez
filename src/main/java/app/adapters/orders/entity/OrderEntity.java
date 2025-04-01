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
	private long orderId;
	private Pet pet;
	private Person owner;
	private User veterinarian;
	private String medicine;
	private String dose;
	private Timestamp date;
}
