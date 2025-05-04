package com.HospitalBooking.HospitalBooking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.HospitalBooking.HospitalBooking.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query
    Optional<User> findByEmail(String email);
	@Query
    Optional<User> findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}