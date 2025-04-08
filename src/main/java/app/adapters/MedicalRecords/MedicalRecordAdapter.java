package app.adapters.MedicalRecords;

import java.util.List;

import app.adapters.MedicalRecords.entity.MedicalRecordEntity;
import app.adapters.MedicalRecords.repository.MedicalRecordRepository;
import app.adapters.orders.OrderAdapter;
import app.adapters.orders.entity.OrderEntity;
import app.adapters.orders.repository.OrderRepository;
import app.adapters.pets.PetAdapter;
import app.adapters.pets.entity.PetEntity;
import app.adapters.pets.repository.PetRepository;
import app.adapters.user.entity.UserEntity;
import app.adapters.user.repository.UserRepository;
import app.domain.models.MedicalRecord;
import app.domain.models.Order;
import app.domain.models.Pet;
import app.domain.models.User;
import app.ports.MedicalRecordPort;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalRecordAdapter implements MedicalRecordPort {

	@Autowired
    private MedicalRecordRepository medicalRecordRepository;
    
    @Autowired
    private PetRepository petRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private PetAdapter petAdapter;
    
    @Autowired
    private OrderAdapter orderAdapter;

    @Override
    public List<MedicalRecord> findByPetId(long petId) {
        List<MedicalRecordEntity> records = medicalRecordRepository.findByPetPetId(petId);
        List<MedicalRecord> result = new ArrayList<>();
        
        for (MedicalRecordEntity entity : records) {
            result.add(mapToMedicalRecord(entity));
        }
        
        return result;
    }

    @Override
    public void save(MedicalRecord medicalRecord) {
        MedicalRecordEntity entity = new MedicalRecordEntity();
        entity.setMedicalRecordId(medicalRecord.getMedicalRecordId());
        entity.setDate(medicalRecord.getDate());
        entity.setReason(medicalRecord.getReason());
        entity.setSymptomatology(medicalRecord.getSymptomatology());
        entity.setDiagnostic(medicalRecord.getDiagnostic());
        entity.setProcedures(medicalRecord.getProcedure());
        entity.setMedicine(medicalRecord.getMedicine());
        entity.setDose(medicalRecord.getDose());
        entity.setVaccination(medicalRecord.getVaccination());
        entity.setAllergyMedication(medicalRecord.getAllergyMedication());
        entity.setProcedureDetails(medicalRecord.getProcedureDetails());
        
        if (medicalRecord.getPet() != null) {
            Optional<PetEntity> petEntityOptional = petRepository.findById(medicalRecord.getPet().getPetId());
            if (petEntityOptional.isPresent()) {
                entity.setPet(petEntityOptional.get());
            }
        }
        
        if (medicalRecord.getVeterinarian() != null) {
            Optional<UserEntity> vetEntityOptional = userRepository.findById(medicalRecord.getVeterinarian().getDocument());
            if (vetEntityOptional.isPresent()) {
                entity.setVeterinarian(vetEntityOptional.get());
            }
        }

        if (medicalRecord.getOrder() != null) {
            Optional<OrderEntity> orderEntityOptional = orderRepository.findById(medicalRecord.getOrder().getOrderId());
            if (orderEntityOptional.isPresent()) {
                entity.setOrder(orderEntityOptional.get());
            }
        }
        
        medicalRecordRepository.save(entity);
        
        medicalRecord.setMedicalRecordId(entity.getMedicalRecordId());
    }

    @Override
    public MedicalRecord findById(long medicalRecordId) {
        Optional<MedicalRecordEntity> medicalRecordOptional = medicalRecordRepository.findById(medicalRecordId);
        if (medicalRecordOptional.isPresent()) {
            return mapToMedicalRecord(medicalRecordOptional.get());
        }
        return null;
    }
    
    private MedicalRecord mapToMedicalRecord(MedicalRecordEntity entity) {
        Pet pet = null;
        if (entity.getPet() != null) {
            pet = petAdapter.findByPetId(entity.getPet().getPetId());
        }
        
        User vet = null;
        if (entity.getVeterinarian() != null) {
            UserEntity vetEntity = entity.getVeterinarian();
            vet = new User(
                vetEntity.getDocument(),
                vetEntity.getName(),
                vetEntity.getAge(),
                vetEntity.getUserName(),
                vetEntity.getPassword(),
                vetEntity.getUserId(),
                vetEntity.getRole()
            );
        }
        
        Order order = null;
        if (entity.getOrder() != null) {
            order = orderAdapter.findByOrderId(entity.getOrder().getOrderId());
        }
        
        return new MedicalRecord(
            entity.getMedicalRecordId(),
            pet,
            entity.getProcedureDetails(),
            entity.getAllergyMedication(),
            entity.getVaccination(),
            order,
            entity.getDose(),
            entity.getMedicine(),
            entity.getProcedure(),
            entity.getDiagnostic(),
            entity.getSymptomatology(),
            entity.getReason(),
            vet,
            entity.getDate()
        );
    }

}
