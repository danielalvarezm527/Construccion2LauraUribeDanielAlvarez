package app.adapters.bills.entity;

import java.sql.Timestamp;

import app.adapters.pets.entity.PetEntity;
import app.adapters.orders.entity.OrderEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bills")
@Getter
@Setter
@NoArgsConstructor
public class BillEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long billId;
    
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private PetEntity pet;
    
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;
    
    private String productName;
    private double value;
    private int amount;
    private Timestamp date;
}
