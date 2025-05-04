package com.HospitalBooking.HospitalBooking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.HospitalBooking.HospitalBooking.model.MedicalRecord;
import com.HospitalBooking.HospitalBooking.model.Patient;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
	@Query
    Optional<MedicalRecord> findByPatient(Patient patient);
}