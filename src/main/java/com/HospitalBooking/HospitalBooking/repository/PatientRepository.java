package com.HospitalBooking.HospitalBooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.HospitalBooking.HospitalBooking.model.Patient;
import com.HospitalBooking.HospitalBooking.model.ServiceProvider;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	@Query
    List<Patient> findByServiceProvider(ServiceProvider serviceProvider);
	@Query
    List<Patient> findByName(String name);
	@Query
	boolean existsByIdNumber(String string);
}