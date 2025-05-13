package app.adapters.rest;

import app.adapters.rest.dto.MedicalRecordRequest;
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
