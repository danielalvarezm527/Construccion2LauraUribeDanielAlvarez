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

    public Bill() {}

    // Getters y setters
    public long getBillId() { return billId; }
    public void setBillId(long billId) { this.billId = billId; }

    public Pet getPetId() { return petId; }
    public void setPetId(Pet petId) { this.petId = petId; }

    public Order getOrderId() { return orderId; }
    public void setOrderId(Order orderId) { this.orderId = orderId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }

    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }

    public Timestamp getDate() { return date; }
    public void setDate(Timestamp date) { this.date = date; }
}
