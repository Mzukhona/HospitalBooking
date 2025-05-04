// MedicalRecordService.java
package com.HospitalBooking.HospitalBooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.HospitalBooking.HospitalBooking.model.MedicalRecord;
import com.HospitalBooking.HospitalBooking.model.Patient;
import com.HospitalBooking.HospitalBooking.repository.MedicalRecordRepository;
import com.HospitalBooking.HospitalBooking.repository.PatientRepository;

@Service
@Transactional
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository,
                                PatientRepository patientRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
        this.patientRepository = patientRepository;
    }

    public MedicalRecord createMedicalRecord(Long patientId, MedicalRecord medicalRecord) throws NotFoundException {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new NotFoundException());
        
        if(patient.getMedicalRecord() != null) {
            throw new IllegalStateException("Patient already has a medical record");
        }
        
        medicalRecord.setPatient(patient);
        return medicalRecordRepository.save(medicalRecord);
    }

    public MedicalRecord getMedicalRecordByPatientId(Long patientId) throws NotFoundException {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new NotFoundException());
        return medicalRecordRepository.findByPatient(patient)
                .orElseThrow(() -> new NotFoundException());
    }

    public MedicalRecord updateMedicalRecord(Long id, MedicalRecord medicalRecordDetails) throws NotFoundException {
        MedicalRecord medicalRecord = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        
        medicalRecord.setHistory(medicalRecordDetails.getHistory());
        medicalRecord.setAllergies(medicalRecordDetails.getAllergies());
        medicalRecord.setMedications(medicalRecordDetails.getMedications());
        return medicalRecordRepository.save(medicalRecord);
    }

    public void deleteMedicalRecord(Long id) throws NotFoundException {
        MedicalRecord medicalRecord = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        medicalRecordRepository.delete(medicalRecord);
    }
}