package com.HospitalBooking.HospitalBooking.config;

import com.HospitalBooking.HospitalBooking.model.User;
import com.HospitalBooking.HospitalBooking.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initDummyUser() {
        // Check if dummy user already exists
        if (userRepository.findByUsername("dummyuser").isEmpty()) {
            User dummyUser = new User();
            dummyUser.setName("John");
            dummyUser.setSurname("Doe");
            dummyUser.setEmail("dummy@hospital.com");
            dummyUser.setUsername("dummyuser");
            dummyUser.setPassword(passwordEncoder.encode("dummypass"));
            dummyUser.setPhoneNumber("123-456-7890");
            dummyUser.setRole("ADMIN");
            
            userRepository.save(dummyUser);
        }
    }
}