package com.HospitalBooking.HospitalBooking.controller;

import com.HospitalBooking.HospitalBooking.model.MedicalRecord;
import com.HospitalBooking.HospitalBooking.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medical-records")
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    @Autowired
    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @PostMapping("/patient/{patientId}")
    public ResponseEntity<MedicalRecord> createMedicalRecord(
            @PathVariable Long patientId,
            @RequestBody MedicalRecord medicalRecord) {
        MedicalRecord createdRecord;
		try {
			createdRecord = medicalRecordService.createMedicalRecord(patientId, medicalRecord);
			return new ResponseEntity<>(createdRecord, HttpStatus.CREATED);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<MedicalRecord> getMedicalRecordByPatient(@PathVariable Long patientId) {
       
		try {
			MedicalRecord record = medicalRecordService.getMedicalRecordByPatientId(patientId);
	        return ResponseEntity.ok(record);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicalRecord> updateMedicalRecord(
            @PathVariable Long id,
            @RequestBody MedicalRecord medicalRecord) {
        MedicalRecord updatedRecord;
		try {
			updatedRecord = medicalRecordService.updateMedicalRecord(id, medicalRecord);
			return ResponseEntity.ok(updatedRecord);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicalRecord(@PathVariable Long id) {
        try {
			medicalRecordService.deleteMedicalRecord(id);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return ResponseEntity.noContent().build();
    }
}