package app.adapters.MedicalRecords.entity;

import java.sql.Timestamp;

import app.domain.models.Order;
import app.domain.models.Person;
import app.domain.models.Pet;
import app.domain.models.User;
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
	private long medicalRecordId;
	private Timestamp date;
	private User veterinarian;
	private String reason; 
	private String symptomatology;
	private String diagnostic;
	private String procedure;
	private String medicine;
	private String dose;
	private Order order;
	private String vaccination;
	private String allergyMedication;
	private String procedureDetails;
	private Pet pet;
}
