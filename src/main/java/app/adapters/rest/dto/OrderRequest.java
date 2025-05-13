package app.adapters.rest.dto;

public class OrderRequest {
    private long petId;
    private long ownerDocument;
    private long veterinarianDocument;
    private String medicine;
    private String dose;

    // Getters y setters
    public long getPetId() { return petId; }
    public void setPetId(long petId) { this.petId = petId; }
    public long getOwnerDocument() { return ownerDocument; }
    public void setOwnerDocument(long ownerDocument) { this.ownerDocument = ownerDocument; }
    public long getVeterinarianDocument() { return veterinarianDocument; }
    public void setVeterinarianDocument(long veterinarianDocument) { this.veterinarianDocument = veterinarianDocument; }
    public String getMedicine() { return medicine; }
    public void setMedicine(String medicine) { this.medicine = medicine; }
    public String getDose() { return dose; }
    public void setDose(String dose) { this.dose = dose; }
}
