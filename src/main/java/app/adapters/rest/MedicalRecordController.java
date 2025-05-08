package app.adapters.rest;

import app.domain.models.MedicalRecord;
import app.domain.models.Pet;
import app.domain.models.Order;
import app.domain.models.User;
import app.ports.MedicalRecordPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/medical-records")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordPort medicalRecordPort;

    @PostMapping
    public ResponseEntity<?> createMedicalRecord(@RequestBody MedicalRecordRequest request) {
        try {
            Pet pet = new Pet();
            pet.setPetId(request.getPetId());
            User veterinarian = new User();
            veterinarian.setDocument(request.getVeterinarianDocument());
            Order order = null;
            if (request.getOrderId() != null) {
                order = new Order();
                order.setOrderId(request.getOrderId());
            }

            MedicalRecord record = new MedicalRecord();
            record.setPet(pet);
            record.setVeterinarian(veterinarian);
            record.setOrder(order);
            record.setDate(new Timestamp(System.currentTimeMillis()));
            record.setReason(request.getReason());
            record.setSymptomatology(request.getSymptomatology());
            record.setDiagnostic(request.getDiagnostic());
            record.setProcedure(request.getProcedure());
            record.setMedicine(request.getMedicine());
            record.setDose(request.getDose());
            record.setVaccination(request.getVaccination());
            record.setAllergyMedication(request.getAllergyMedication());
            record.setProcedureDetails(request.getProcedureDetails());

            medicalRecordPort.save(record);
            return ResponseEntity.ok(record);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/pet/{petId}")
    public ResponseEntity<?> getRecordsByPet(@PathVariable long petId) {
        List<MedicalRecord> records = medicalRecordPort.findByPetId(petId);
        if (records == null || records.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(records);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRecordById(@PathVariable long id) {
        MedicalRecord record = medicalRecordPort.findById(id);
        if (record == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(record);
    }
}

// DTO para la petición
class MedicalRecordRequest {
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
