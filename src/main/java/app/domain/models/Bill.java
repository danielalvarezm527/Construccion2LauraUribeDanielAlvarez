package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class Bill {
	private long billId;
    private Pet petId;
    private Order orderId;
    private String productName;
    private double value;
    private int amount;
    private Timestamp date; 
    
    public Bill(long billId, Pet petId, Order orderId, String productName, double value, int amount, Timestamp date) {
        this.billId = billId;
        this.petId = petId;
        this.orderId = orderId;
        this.productName = productName;
        this.value = value;
        this.amount = amount;
        this.date = date;
    }
}
