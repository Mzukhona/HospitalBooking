// ServiceProviderController.java
package com.HospitalBooking.HospitalBooking.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.HospitalBooking.HospitalBooking.DTOs.ProviderRegistrationDTO;
import com.HospitalBooking.HospitalBooking.model.ServiceProvider;
import com.HospitalBooking.HospitalBooking.service.ServiceProviderService;

@RestController
@RequestMapping("/api/providers")
public class ServiceProviderController {
	@Autowired
    private final ServiceProviderService serviceProviderService;
    
 

    @Autowired
    public ServiceProviderController(ServiceProviderService serviceProviderService) {
        this.serviceProviderService = serviceProviderService;
    }
    

    @PostMapping("/register")
    public ResponseEntity<?> registerProvider(@RequestBody ProviderRegistrationDTO dto) {
        try {
            ServiceProvider provider = serviceProviderService.createServiceProvider(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(provider);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/specialization/{specialization}")
    public ResponseEntity<List<ServiceProvider>> getBySpecialization(@PathVariable String specialization) {
        List<ServiceProvider> providers = serviceProviderService.getProvidersBySpecialization(specialization);
        return ResponseEntity.ok(providers);
    }
    @GetMapping
    public ResponseEntity<List<ServiceProvider>> getServiceProviders() {
        List<ServiceProvider> providers = serviceProviderService.findAll();
        return ResponseEntity.ok(providers);
    }

    @GetMapping("/hospital/{hospitalId}")
    public ResponseEntity<Set<ServiceProvider>> getByHospital(@PathVariable Long hospitalId) {
        Set<ServiceProvider> providers = serviceProviderService.getProvidersByHospital(hospitalId);
        return ResponseEntity.ok(providers);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ServiceProvider>> searchByHospitalDomain(@RequestParam String domain) {
        List<ServiceProvider> providers = serviceProviderService.searchProvidersByHospitalDomain(domain);
        return ResponseEntity.ok(providers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceProvider> updateServiceProvider(
            @PathVariable Long id,
            @RequestBody ServiceProvider providerDetails) {
        ServiceProvider updatedProvider;
		try {
			updatedProvider = serviceProviderService.updateServiceProvider(id, providerDetails);
			 return ResponseEntity.ok(updatedProvider);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceProvider(@PathVariable Long id) {
        try {
			serviceProviderService.deleteServiceProvider(id);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return ResponseEntity.noContent().build();
    }
}