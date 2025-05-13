package app.adapters.rest.dto;

public class BillRequest {
    private Long petId;
    private Long orderId;
    private String productName;
    private double value;
    private int amount;

    // Getters y setters
    public Long getPetId() { return petId; }
    public void setPetId(Long petId) { this.petId = petId; }
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }
    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }
}
