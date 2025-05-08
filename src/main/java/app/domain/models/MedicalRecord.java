package app.domain.models;

import java.sql.Timestamp;

public class MedicalRecord {
    private long medicalRecordId;
    private Pet pet;
    private Timestamp date;
    private String reason;
    private String symptomatology;
    private String diagnostic;
    private String procedure;
    private String medicine;
    private String dose;
    private Order order;
    private User veterinarian;
    private String vaccination;
    private String allergyMedication;
    private String procedureDetails;

    // Constructor vacío
    public MedicalRecord() {}

    // Constructor completo (opcional)
    public MedicalRecord(long medicalRecordId, Pet pet, Timestamp date, String reason, String symptomatology, String diagnostic,
                        String procedure, String medicine, String dose, Order order, User veterinarian,
                        String vaccination, String allergyMedication, String procedureDetails) {
        this.medicalRecordId = medicalRecordId;
        this.pet = pet;
        this.date = date;
        this.reason = reason;
        this.symptomatology = symptomatology;
        this.diagnostic = diagnostic;
        this.procedure = procedure;
        this.medicine = medicine;
        this.dose = dose;
        this.order = order;
        this.veterinarian = veterinarian;
        this.vaccination = vaccination;
        this.allergyMedication = allergyMedication;
        this.procedureDetails = procedureDetails;
    }

    // Constructor completo para el adapter (según error reportado)
    public MedicalRecord(
        long medicalRecordId,
        Pet pet,
        String reason,
        String symptomatology,
        String diagnostic,
        Order order,
        String procedure,
        String medicine,
        String dose,
        String vaccination,
        String allergyMedication,
        String procedureDetails,
        User veterinarian,
        java.sql.Timestamp date
    ) {
        this.medicalRecordId = medicalRecordId;
        this.pet = pet;
        this.reason = reason;
        this.symptomatology = symptomatology;
        this.diagnostic = diagnostic;
        this.order = order;
        this.procedure = procedure;
        this.medicine = medicine;
        this.dose = dose;
        this.vaccination = vaccination;
        this.allergyMedication = allergyMedication;
        this.procedureDetails = procedureDetails;
        this.veterinarian = veterinarian;
        this.date = date;
    }

    // Getters y setters
    public long getMedicalRecordId() { return medicalRecordId; }
    public void setMedicalRecordId(long medicalRecordId) { this.medicalRecordId = medicalRecordId; }

    public Pet getPet() { return pet; }
    public void setPet(Pet pet) { this.pet = pet; }

    public Timestamp getDate() { return date; }
    public void setDate(Timestamp date) { this.date = date; }

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

    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }

    public User getVeterinarian() { return veterinarian; }
    public void setVeterinarian(User veterinarian) { this.veterinarian = veterinarian; }

    public String getVaccination() { return vaccination; }
    public void setVaccination(String vaccination) { this.vaccination = vaccination; }

    public String getAllergyMedication() { return allergyMedication; }
    public void setAllergyMedication(String allergyMedication) { this.allergyMedication = allergyMedication; }

    public String getProcedureDetails() { return procedureDetails; }
    public void setProcedureDetails(String procedureDetails) { this.procedureDetails = procedureDetails; }
}
