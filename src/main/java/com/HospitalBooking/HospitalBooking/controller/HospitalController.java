package com.HospitalBooking.HospitalBooking.controller;

import com.HospitalBooking.HospitalBooking.model.Hospital;
import com.HospitalBooking.HospitalBooking.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {

    private final HospitalService hospitalService;

    @Autowired
    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @PostMapping
    public ResponseEntity<Hospital> createHospital(@RequestBody Hospital hospital) {
        Hospital createdHospital = hospitalService.createHospital(hospital);
        return ResponseEntity.ok(createdHospital);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Hospital>> searchHospitals(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String specialization) {
        if (name != null) {
            return ResponseEntity.ok(hospitalService.searchHospitalsByName(name));
        }
        if (address != null) {
            return ResponseEntity.ok(hospitalService.searchHospitalsByAddress(address));
        }
        if (specialization != null) {
            return ResponseEntity.ok(hospitalService.searchHospitalsBySpecialization(specialization));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/{hospitalId}/providers/{providerId}")
    public ResponseEntity<Hospital> addServiceProvider(
            @PathVariable Long hospitalId,
            @PathVariable Long providerId) {
        Hospital hospital;
		try {
			hospital = hospitalService.addServiceProviderToHospital(hospitalId, providerId);
			return ResponseEntity.ok(hospital);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }

    @DeleteMapping("/{hospitalId}/providers/{providerId}")
    public ResponseEntity<Void> removeServiceProvider(
            @PathVariable Long hospitalId,
            @PathVariable Long providerId) {
        try {
			hospitalService.removeServiceProviderFromHospital( providerId);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return ResponseEntity.noContent().build();
    }
}