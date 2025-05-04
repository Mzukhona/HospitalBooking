package com.HospitalBooking.HospitalBooking.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import com.HospitalBooking.HospitalBooking.model.ServiceProvider;

public interface ServiceProviderRepository extends JpaRepository<ServiceProvider, Long> {
	@Query
    List<ServiceProvider> findBySpecialization(String specialization);
	@Query
    Set<ServiceProvider> findByHospitalId(Long hospitalId);
	@Query
    List<ServiceProvider> findByEmailContaining(String hospitalDomain);
	@NonNull
	List<ServiceProvider> findAll();
}