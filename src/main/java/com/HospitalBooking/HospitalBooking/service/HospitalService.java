package com.HospitalBooking.HospitalBooking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.HospitalBooking.HospitalBooking.model.Hospital;
import com.HospitalBooking.HospitalBooking.model.ServiceProvider;
import com.HospitalBooking.HospitalBooking.repository.HospitalRepository;
import com.HospitalBooking.HospitalBooking.repository.ServiceProviderRepository;

@Service
@Transactional
public class HospitalService {

    private final HospitalRepository hospitalRepository;
    private final ServiceProviderRepository serviceProviderRepository;

    @Autowired
    public HospitalService(HospitalRepository hospitalRepository,
                           ServiceProviderRepository serviceProviderRepository) {
        this.hospitalRepository = hospitalRepository;
        this.serviceProviderRepository = serviceProviderRepository;
    }

    public Hospital createHospital(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    public List<Hospital> searchHospitalsByName(String name) {
        return hospitalRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Hospital> searchHospitalsByAddress(String address) {
        return hospitalRepository.findByAddressContainingIgnoreCase(address);
    }

    public List<Hospital> searchHospitalsBySpecialization(String specialization) {
        return hospitalRepository.findByServiceProviders_Specialization(specialization);
    }

    public Hospital addServiceProviderToHospital(Long hospitalId, Long providerId) throws NotFoundException {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new NotFoundException());
        ServiceProvider provider = serviceProviderRepository.findById(providerId)
                .orElseThrow(() -> new NotFoundException());
        
        // Set the hospital for the provider
        provider.setHospitals(hospital);
        serviceProviderRepository.save(provider);
        
        // The hospital's serviceProviders collection will be updated automatically
        // because of the mappedBy relationship
        return hospital;
    }

    public void removeServiceProviderFromHospital(Long providerId) throws NotFoundException {
        ServiceProvider provider = serviceProviderRepository.findById(providerId)
                .orElseThrow(() -> new NotFoundException());
        
        // Remove the hospital association
        provider.setHospitals(null);
        serviceProviderRepository.save(provider);
        
        // The hospital's serviceProviders collection will be updated automatically
    }
}