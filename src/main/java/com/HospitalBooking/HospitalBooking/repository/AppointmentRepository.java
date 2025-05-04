package com.HospitalBooking.HospitalBooking.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.HospitalBooking.HospitalBooking.model.Appointment;
import com.HospitalBooking.HospitalBooking.model.Patient;
import com.HospitalBooking.HospitalBooking.model.ServiceProvider;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	@Query
    List<Appointment> findByPatient(Patient patient);
	@Query
    List<Appointment> findByServiceProvider(ServiceProvider serviceProvider);
	@Query
    List<Appointment> findByDateTimeBetween(LocalDateTime start, LocalDateTime end);
	@Query
    List<Appointment> findByPatientAndDateTimeAfter(Patient patient, LocalDateTime dateTime);
}