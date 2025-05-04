package com.HospitalBooking.HospitalBooking.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.HospitalBooking.HospitalBooking.DTOs.ProviderRegistrationDTO;
import com.HospitalBooking.HospitalBooking.model.ServiceProvider;
import com.HospitalBooking.HospitalBooking.repository.ServiceProviderRepository;

@Service
@Transactional
public class ServiceProviderService {
	
	 @Autowired
    private final ServiceProviderRepository serviceProviderRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public ServiceProviderService(ServiceProviderRepository serviceProviderRepository) {
        this.serviceProviderRepository = serviceProviderRepository;
    }

    public ServiceProvider createServiceProvider(ProviderRegistrationDTO dto) {
        ServiceProvider provider = new ServiceProvider();
        // Set inherited User fields
        provider.setUsername(dto.getUsername());
        provider.setPassword(passwordEncoder.encode(dto.getPassword()));
        provider.setEmail(dto.getEmail());
        provider.setName(dto.getName());
        provider.setSurname(dto.getSurname());
        provider.setPhoneNumber(dto.getPhoneNumber());
        provider.setHospitals(dto.getHospital());
        // Set Provider-specific fields
        provider.setSpecialization(dto.getSpecialization());

        return serviceProviderRepository.save(provider);
    }

    public List<ServiceProvider> getProvidersBySpecialization(String specialization) {
        return serviceProviderRepository.findBySpecialization(specialization);
    }

    public Set<ServiceProvider> getProvidersByHospital(Long hospitalId) {
        return serviceProviderRepository.findByHospitalId(hospitalId);
    }

    public List<ServiceProvider> searchProvidersByHospitalDomain(String domain) {
        return serviceProviderRepository.findByEmailContaining(domain);
    }
    public List<ServiceProvider> findAll() {
        return serviceProviderRepository.findAll();
    }

    public ServiceProvider updateServiceProvider(Long id, ServiceProvider providerDetails) throws NotFoundException {
        ServiceProvider provider = serviceProviderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        
        provider.setName(providerDetails.getName());
        provider.setSurname(providerDetails.getSurname());
        provider.setEmail(providerDetails.getEmail());
        provider.setSpecialization(providerDetails.getSpecialization());
        provider.setPhoneNumber(providerDetails.getPhoneNumber());
        
        return serviceProviderRepository.save(provider);
    }

    public void deleteServiceProvider(Long id) throws NotFoundException {
        ServiceProvider provider = serviceProviderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        serviceProviderRepository.delete(provider);
    }
}