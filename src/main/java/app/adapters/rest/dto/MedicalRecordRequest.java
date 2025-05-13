package app.adapters.rest.dto;

public class MedicalRecordRequest {
    private long petId;
    private Long orderId;
    private long veterinarianDocument;
    private String reason;
    private String symptomatology;
    private String diagnostic;
    private String procedure;
    private String medicine;
    private String dose;
    private String vaccination;
    private String allergyMedication;
    private String procedureDetails;

    // Getters y setters
    public long getPetId() { return petId; }
    public void setPetId(long petId) { this.petId = petId; }
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }
    public long getVeterinarianDocument() { return veterinarianDocument; }
    public void setVeterinarianDocument(long veterinarianDocument) { this.veterinarianDocument = veterinarianDocument; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public String getSymptomatology() { return symptomatology; }
    public void setSymptomatology(String symptomatology) { this.symptomatology = symptomatology; }
    public String getDiagnostic() { return diagnostic; }
    public void setDiagnostic(String diagnostic) { this.diagnostic = diagnostic; }
    public String getProcedure() { return procedure; }
    public void setProcedure(String procedure) { this.procedure = procedure; }
    public String getMedicine() { return medicine; }
    public void setMedicine(String medicine) { this.medicine = medicine; }
    public String getDose() { return dose; }
    public void setDose(String dose) { this.dose = dose; }
    public String getVaccination() { return vaccination; }
    public void setVaccination(String vaccination) { this.vaccination = vaccination; }
    public String getAllergyMedication() { return allergyMedication; }
    public void setAllergyMedication(String allergyMedication) { this.allergyMedication = allergyMedication; }
    public String getProcedureDetails() { return procedureDetails; }
    public void setProcedureDetails(String procedureDetails) { this.procedureDetails = procedureDetails; }
}
