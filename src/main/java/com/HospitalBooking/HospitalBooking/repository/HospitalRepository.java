package com.HospitalBooking.HospitalBooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.HospitalBooking.HospitalBooking.model.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
	@Query
    List<Hospital> findByNameContainingIgnoreCase(String name);
	@Query
    List<Hospital> findByAddressContainingIgnoreCase(String address);
	@Query
    List<Hospital> findByServiceProviders_Specialization(String specialization);
}