package com.HospitalBooking.HospitalBooking.controller;

import java.time.LocalDateTime;
import java.util.List;

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

import com.HospitalBooking.HospitalBooking.model.Appointment;
import com.HospitalBooking.HospitalBooking.service.AppointmentService;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment)  {
       
		try {
			Appointment createdAppointment = appointmentService.createAppointment(appointment);
	        return new ResponseEntity<>(createdAppointment, HttpStatus.CREATED);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByPatient(@PathVariable Long patientId) throws NotFoundException {
        List<Appointment> appointments = appointmentService.getAppointmentsByPatient(patientId);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/provider/{providerId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByProvider(@PathVariable Long providerId) {
        List<Appointment> appointments;
		try {
			appointments = appointmentService.getAppointmentsByServiceProvider(providerId);
			return ResponseEntity.ok(appointments);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }

    @GetMapping("/between")
    public ResponseEntity<List<Appointment>> getAppointmentsBetweenDates(
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {
        List<Appointment> appointments = appointmentService.getAppointmentsBetweenDates(start, end);
        return ResponseEntity.ok(appointments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(
            @PathVariable Long id, 
            @RequestBody Appointment appointmentDetails) {
       
		try {
			Appointment updatedAppointment = appointmentService.updateAppointment(id, appointmentDetails);
	        return ResponseEntity.ok(updatedAppointment);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        try {
        	appointmentService.deleteAppointment(id);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return ResponseEntity.noContent().build();
    }
}