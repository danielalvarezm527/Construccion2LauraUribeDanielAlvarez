package app.domain.services;

import app.domain.models.*;
import app.ports.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if (person != null) {
            throw new Exception("Ya existe un propietario con esa cedula");
        }
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
        if(pet.getPetId() == order.getPet().getPetId()){
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





}
