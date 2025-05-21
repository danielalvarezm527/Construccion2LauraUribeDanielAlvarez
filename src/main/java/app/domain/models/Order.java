package app.domain.models;

import java.sql.Timestamp;

public class Order {
    private long orderId;
    private Pet pet;
    private Person owner;
    private User veterinarian;
    private String medicine;
    private String dose;
    private Timestamp date;
    private boolean cancelled;

    // Constructor vacío
    public Order() {}

    // Constructor completo
    public Order(long orderId, Pet pet, Person owner, User veterinarian, String medicine, String dose, Timestamp date) {
        this.orderId = orderId;
        this.pet = pet;
        this.owner = owner;
        this.veterinarian = veterinarian;
        this.medicine = medicine;
        this.dose = dose;
        this.date = date;
        this.cancelled = false;
    }

    // Getters y setters
    public long getOrderId() { return orderId; }
    public void setOrderId(long orderId) { this.orderId = orderId; }

    public Pet getPet() { return pet; }
    public void setPet(Pet pet) { this.pet = pet; }

    public Person getOwner() { return owner; }
    public void setOwner(Person owner) { this.owner = owner; }

    public User getVeterinarian() { return veterinarian; }
    public void setVeterinarian(User veterinarian) { this.veterinarian = veterinarian; }

    public String getMedicine() { return medicine; }
    public void setMedicine(String medicine) { this.medicine = medicine; }

    public String getDose() { return dose; }
    public void setDose(String dose) { this.dose = dose; }

    public Timestamp getDate() { return date; }
    public void setDate(Timestamp date) { this.date = date; }

    public boolean isCancelled() { return cancelled; }
    public void setCancelled(boolean cancelled) { this.cancelled = cancelled; }
}

