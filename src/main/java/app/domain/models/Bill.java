package app.domain.models;

import java.sql.Timestamp;

public class Bill {
	private long billId;
	private Pet petId;
	private Order orderId;
	private String productName;
	private double value;
	private int amount;
	private Timestamp date; 
	
}
