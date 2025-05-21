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
        // Verificar que la mascota exista
        Pet pet = petPort.findByPetId(medicalRecord.getPet().getPetId());
        if (pet == null) {
            throw new Exception("Esta mascota no existe");
        }
        
        // Verificar que el veterinario exista
        User veterinarian = null;
        // Primero intentar buscar por ID si está disponible
        if (medicalRecord.getVeterinarian().getUserId() > 0) {
            veterinarian = userPort.findByUserId(medicalRecord.getVeterinarian().getUserId());
        }
        
        // Luego intentar buscar por documento
        if (veterinarian == null) {
            List<User> allUsers = userPort.findAll(); // Necesitarías agregar este método a UserPort
            for (User user : allUsers) {
                if (user.getDocument() == medicalRecord.getVeterinarian().getDocument()) {
                    veterinarian = user;
                    break;
                }
            }
        }
        
        if (veterinarian == null || !veterinarian.getRole().equalsIgnoreCase("Veterinarian")) {
            throw new Exception("El veterinario no existe o no tiene el rol adecuado");
        }
        
        // Hacer la orden opcional
        if (medicalRecord.getOrder() != null && medicalRecord.getOrder().getOrderId() > 0) {
            Order order = orderPort.findByOrderId(medicalRecord.getOrder().getOrderId());
            if (order == null) {
                throw new Exception("La orden especificada no existe");
            }
            
            if (pet.getPetId() != order.getPet().getPetId()) {
                throw new Exception("Esta mascota no está asociada a esta orden");
            }
            
            medicalRecord.setOrder(order);
        }
        
        // Asignar los valores correctos
        medicalRecord.setVeterinarian(veterinarian);
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
        // Verificar que el veterinario exista
        User veterinarian = null;
        // Primero intentar buscar por ID si está disponible
        if (order.getVeterinarian().getUserId() > 0) {
            veterinarian = userPort.findByUserId(order.getVeterinarian().getUserId());
        }
        
        // Luego intentar buscar por documento
        if (veterinarian == null) {
            List<User> allUsers = userPort.findAll(); // Necesitarías agregar este método a UserPort
            for (User user : allUsers) {
                if (user.getDocument() == order.getVeterinarian().getDocument()) {
                    veterinarian = user;
                    break;
                }
            }
        }
        
        if (veterinarian == null || !veterinarian.getRole().equalsIgnoreCase("Veterinarian")) {
            throw new Exception("El veterinario no existe o no tiene el rol adecuado");
        }
        
        // Verificar mascota
        Pet pet = petPort.findByPetId(order.getPet().getPetId());
        if (pet == null) {
            throw new Exception("La mascota no existe");
        }
        
        // Verificar dueño
        Person owner = personPort.findByPersonId(order.getOwner().getDocument());
        if (owner == null) {
            throw new Exception("El dueño no existe");
        }
        
        if (owner.getDocument() != pet.getOwner().getDocument()) {
            throw new Exception("El dueño no es dueño de esta mascota");
        }
        
        // Asignar el veterinario correcto
        order.setVeterinarian(veterinarian);
        order.setDate(new Timestamp(System.currentTimeMillis()));
        
        orderPort.save(order);
    }

    public void cancelOrder(Order order) throws Exception {
        Order db_order = orderPort.findByOrderId(order.getOrderId());
        if (db_order == null) {
            throw new Exception("La orden no existe");
        }
        
        // Verificar que el veterinario exista
        User veterinarian = null;
        // Primero intentar buscar por ID si está disponible
        if (order.getVeterinarian().getUserId() > 0) {
            veterinarian = userPort.findByUserId(order.getVeterinarian().getUserId());
        }
        
        // Luego intentar buscar por documento
        if (veterinarian == null) {
            List<User> allUsers = userPort.findAll();
            for (User user : allUsers) {
                if (user.getDocument() == order.getVeterinarian().getDocument()) {
                    veterinarian = user;
                    break;
                }
            }
        }
        
        if (veterinarian == null || !veterinarian.getRole().equalsIgnoreCase("Veterinarian")) {
            throw new Exception("Solo un veterinario puede anular órdenes");
        }
        
        // Marcar la orden como cancelada
        db_order.setCancelled(true);
        orderPort.save(db_order);
        
        // Crear registro médico de la anulación
        MedicalRecord cancelRecord = new MedicalRecord();
        cancelRecord.setPet(db_order.getPet());
        cancelRecord.setVeterinarian(veterinarian);
        cancelRecord.setDate(new Timestamp(System.currentTimeMillis()));
        
        cancelRecord.setReason("Anulación de orden #" + db_order.getOrderId());
        cancelRecord.setProcedureDetails("Orden anulada por " + veterinarian.getName());
        cancelRecord.setOrder(db_order);
        
        medicalRecordPort.save(cancelRecord);
    }
}
