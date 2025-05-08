package app.ports;

import app.domain.models.MedicalRecord;

import java.util.List;

public interface MedicalRecordPort {
    public List<MedicalRecord> findByPetId(long pet);
    public void save(MedicalRecord medicalRecord);
    public MedicalRecord findById(long medicalRecordId);
}
