package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
public class Order{
	private long orderId;
	private Pet pet;
	private Person owner;
	private User veterinarian;
	private String medicine;
	private String dose;
	private Timestamp date;

	public Order(long orderId, Pet pet, Person owner, User veterinarian, String medicine, String dose, Timestamp date) {
		this.orderId = orderId;
		this.pet = pet;
		this.owner = owner;
		this.veterinarian = veterinarian;
		this.medicine = medicine;
		this.dose = dose;
		this.date = date;
	}

	public boolean isCancelled() {
		// TODO Auto-generated method stub
		return false;
	}
}

