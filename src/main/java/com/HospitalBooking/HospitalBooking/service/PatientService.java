package com.HospitalBooking.HospitalBooking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.HospitalBooking.HospitalBooking.DTOs.PatientRegistrationDTO;
import com.HospitalBooking.HospitalBooking.model.Patient;
import com.HospitalBooking.HospitalBooking.model.ServiceProvider;
import com.HospitalBooking.HospitalBooking.repository.PatientRepository;
import com.HospitalBooking.HospitalBooking.repository.ServiceProviderRepository;

@Service
@Transactional
public class PatientService {
	
	 @Autowired
    private final PatientRepository patientRepository;
    @Autowired
    private final ServiceProviderRepository serviceProviderRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public PatientService(PatientRepository patientRepository,
                          ServiceProviderRepository serviceProviderRepository) {
        this.patientRepository = patientRepository;
        this.serviceProviderRepository = serviceProviderRepository;
    }

    public Patient createPatient(PatientRegistrationDTO dto) {
 
    	if (patientRepository.existsByIdNumber(dto.getIdNumber())) {
    	    throw new IllegalArgumentException("ID Number already exists"); 
    	}

      
        ServiceProvider provider = serviceProviderRepository.findById(dto.getServiceProviderId())
            .orElseThrow(() -> new IllegalArgumentException("Provider not found"));

       
        Patient patient = new Patient();
      
        patient.setUsername(dto.getUsername());
        patient.setPassword(passwordEncoder.encode(dto.getPassword()));
        patient.setEmail(dto.getEmail());
        patient.setName(dto.getName());
        patient.setSurname(dto.getSurname());
        patient.setPhoneNumber(dto.getPhoneNumber());
        // Set Patient-specific fields
        patient.setDateOfBirth(dto.getDateOfBirth());
        patient.setGender(dto.getGender());
        patient.setServiceProvider(provider);
        patient.setMedicalRecord(dto.getMedical());

        return patientRepository.save(patient);
    }


    public List<Patient> getPatientsByServiceProvider(Long providerId) throws NotFoundException {
        ServiceProvider provider = serviceProviderRepository.findById(providerId)
                .orElseThrow(() -> new NotFoundException());
        return patientRepository.findByServiceProvider(provider);
    }

    public List<Patient> searchPatientsByName(String name) {
        return patientRepository.findByName(name);
    }

    public Patient updatePatient(Long id, Patient patientDetails) throws NotFoundException {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        
        patient.setName(patientDetails.getName());
        patient.setSurname(patientDetails.getSurname());
        patient.setEmail(patientDetails.getEmail());
        patient.setPhoneNumber(patientDetails.getPhoneNumber());
        patient.setDateOfBirth(patientDetails.getDateOfBirth());
        patient.setGender(patientDetails.getGender());
        
        return patientRepository.save(patient);
    }

    public void deletePatient(Long id) throws NotFoundException {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        patientRepository.delete(patient);
    }
}