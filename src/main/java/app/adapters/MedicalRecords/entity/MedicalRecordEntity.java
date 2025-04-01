package app.adapters.MedicalRecords.entity;

import java.sql.Timestamp;

import app.adapters.pets.entity.PetEntity;
import app.adapters.orders.entity.OrderEntity;
import app.adapters.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MedicalRecords")
@Getter
@Setter
@NoArgsConstructor
public class MedicalRecordEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long medicalRecordId;
    
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private PetEntity pet;
    
    @ManyToOne
    @JoinColumn(name = "veterinarian_id")
    private UserEntity veterinarian;
    
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;
    
    private Timestamp date;
    private String reason;
    private String symptomatology;
    private String diagnostic;
    private String procedure;
    private String medicine;
    private String dose;
    private String vaccination;
    private String allergyMedication;
    private String procedureDetails;
}
