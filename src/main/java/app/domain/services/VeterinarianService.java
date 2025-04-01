package app.domain.services;

import app.domain.models.*;
import app.ports.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@Service
@NoArgsConstructor
public class VeterinarianService {
    @Autowired
    private PersonPort personPort;
    @Autowired
    private UserPort userPort;
    @Autowired
    private PetPort petPort;
    @Autowired
    private OrderPort orderPort;
    @Autowired
    private MedicalRecordPort medicalRecordPort;

    public void createOwner(Person personOwner) throws Exception {
        Person person = personPort.findByPersonId(personOwner.getDocument());
        if (person!= null) {
            throw new Exception(" existe Propietario con esa cedula");
        }
        personOwner.setRole("Dueño");
        personPort.save(personOwner);
    }

    public void createPet(Pet pet) throws Exception {
        Pet pet1  = petPort.findByPetId(pet.getPetId());
        if(pet1!=null){
            throw new Exception("Esta mascota ya existe");
        }
        Person person = personPort.findByPersonId(pet.getOwner().getDocument());
        if (person == null) {
            throw new Exception("No existe un propietario con esa cedula");
        }
        pet.setOwner(person);
        petPort.save(pet);
    }

    public void createMedicalRecord(MedicalRecord medicalRecord) throws Exception {
        User user = userPort.findByUserId(medicalRecord.getVeterinarian().getDocument());
        if (user == null || !user.getRole().equals("veterinarian")) {
            throw new Exception("no existe un usuario con esa cedula.");
        }

        Order order = orderPort.findByOrderId(medicalRecord.getOrder().getOrderId());
        if (order == null) {
            throw new Exception("Esta historia médica no cuenta con una Orden");
        }
        
        Pet pet  = petPort.findByPetId(medicalRecord.getPet().getPetId());
        if(pet==null){
            throw new Exception("Esta mascota no existe");
        }
        if(pet.getPetId() != order.getPet().getPetId()){
            throw new Exception("Esta mascota no esta asociada a esta orden");
        }
        medicalRecord.setVeterinarian(order.getVeterinarian());
        medicalRecord.setOrder(order);
        medicalRecord.setPet(pet);
        medicalRecordPort.save(medicalRecord);
    }

    public List<MedicalRecord> searchAllMedicalRecordByPetId(Pet pet) throws Exception {
        pet =petPort.findByPetId(pet.getPetId());
        if(pet==null){
            throw new Exception("Esta mascota no existe");
        }
        List<MedicalRecord> newMedicalRecords = medicalRecordPort.findByPetId(pet.getPetId());
        if (newMedicalRecords == null) {
            throw new Exception("No existe historia clinica para esta mascota");
        }
        return newMedicalRecords;
    }

    public MedicalRecord searchMedicalRecordById(long medicalRecordId) throws Exception {
        MedicalRecord newMedicalRecord = medicalRecordPort.findById(medicalRecordId);
        if (newMedicalRecord == null) {
            throw new Exception("No existe historia clinica para esta mascota");
        }
        return newMedicalRecord;
    }

    public void createOrder(Order order) throws Exception {
        User veterinarian = userPort.findByUserId(order.getVeterinarian().getDocument());
        if (veterinarian == null || !veterinarian.getRole().equals("Veterinarian")) {
            throw new Exception("El veterinario no existe o no tiene el rol adecuado");
        }
        
        Pet pet = petPort.findByPetId(order.getPet().getPetId());
        if (pet == null) {
            throw new Exception("La mascota no existe");
        }
        
        Person owner = personPort.findByPersonId(order.getOwner().getDocument());
        if (owner == null) {
            throw new Exception("El dueño no existe");
        }
        if (owner.getDocument() != pet.getOwner().getDocument()) {
            throw new Exception("El dueño no es dueño de esta mascota");
        }
        
        order.setOrderId(System.currentTimeMillis());
        order.setDate(new Timestamp(System.currentTimeMillis()));
        
        orderPort.save(order);
    }

    public void cancelOrder(Order order) throws Exception {
        Order db_order = orderPort.findByOrderId(order.getOrderId());
        if (db_order == null) {
            throw new Exception("La orden no existe");
        }
        if (order.getVeterinarian() == null || userPort.findByUserId(order.getVeterinarian().getDocument()) == null || !order.getVeterinarian().getRole().equals("Veterinarian")) {
            throw new Exception("Solo un veterinario puede anular órdenes");
        }
        
        MedicalRecord cancelRecord = new MedicalRecord();
        cancelRecord.setPet(db_order.getPet());
        cancelRecord.setVeterinarian(order.getVeterinarian());

        cancelRecord.setDate(new Timestamp(System.currentTimeMillis()));
        
        cancelRecord.setReason("Anulación de orden #" + db_order.getOrderId());
        cancelRecord.setProcedureDetails("Orden anulada por " + order.getVeterinarian().getName());
        cancelRecord.setOrder(db_order);
        
        cancelRecord.setMedicalRecordId(System.currentTimeMillis());
        
        medicalRecordPort.save(cancelRecord);
    }
}
