package app.domain.models;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
public class MedicalRecord {
	private Timestamp date;
	private long medicalRecordId;
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

	public MedicalRecord(Long medicalRecordId, Pet pet, String procedureDetails, String allergyMedication, String vaccination, Order order, String dose, String medicine, String procedure, String diagnostic, String symptomatology, String reason, User veterinarian, Timestamp date) {
		this.procedureDetails = procedureDetails;
		this.allergyMedication = allergyMedication;
		this.vaccination = vaccination;
		this.order = order;
		this.dose = dose;
		this.medicine = medicine;
		this.procedure = procedure;
		this.diagnostic = diagnostic;
		this.symptomatology = symptomatology;
		this.reason = reason;
		this.veterinarian = veterinarian;
		this.date = date;
		this.pet = pet;
		this.medicalRecordId=medicalRecordId;
	}
}
