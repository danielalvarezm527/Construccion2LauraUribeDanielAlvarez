package app.adapters.bills.entity;

import java.sql.Timestamp;

import app.domain.models.Order;
import app.domain.models.Pet;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Bills")
@Getter
@Setter
@NoArgsConstructor
public class BillEntity {
	@Id
	private long billId;
    private Pet petId;
    private Order orderId;
    private String productName;
    private double value;
    private int amount;
    private Timestamp date; 
}
