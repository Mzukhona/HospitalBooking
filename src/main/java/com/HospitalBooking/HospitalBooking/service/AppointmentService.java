package com.HospitalBooking.HospitalBooking.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.HospitalBooking.HospitalBooking.model.Appointment;
import com.HospitalBooking.HospitalBooking.model.Patient;
import com.HospitalBooking.HospitalBooking.model.ServiceProvider;
import com.HospitalBooking.HospitalBooking.repository.AppointmentRepository;
import com.HospitalBooking.HospitalBooking.repository.PatientRepository;
import com.HospitalBooking.HospitalBooking.repository.ServiceProviderRepository;

@Service
@Transactional
public class AppointmentService  {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final ServiceProviderRepository serviceProviderRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository,
                              PatientRepository patientRepository,
                              ServiceProviderRepository serviceProviderRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.serviceProviderRepository = serviceProviderRepository;
    }

    public Appointment createAppointment(Appointment appointment) throws NotFoundException {
        Patient patient = patientRepository.findById(appointment.getPatient().getId())
                .orElseThrow(() -> new NotFoundException());
        ServiceProvider provider = serviceProviderRepository.findById(appointment.getServiceProvider().getId())
                .orElseThrow(() -> new NotFoundException());

        appointment.setPatient(patient);
        appointment.setServiceProvider(provider);
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointmentsByPatient(Long patientId) throws NotFoundException {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new NotFoundException());
        return appointmentRepository.findByPatient(patient);
    }

    public List<Appointment> getAppointmentsByServiceProvider(Long providerId) throws NotFoundException {
        ServiceProvider provider = serviceProviderRepository.findById(providerId)
                .orElseThrow(() -> new NotFoundException());
        return appointmentRepository.findByServiceProvider(provider);
    }

    public List<Appointment> getAppointmentsBetweenDates(LocalDateTime start, LocalDateTime end) {
        return appointmentRepository.findByDateTimeBetween(start, end);
    }

    public Appointment updateAppointment(Long id, Appointment appointmentDetails) throws NotFoundException {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        
        appointment.setDateTime(appointmentDetails.getDateTime());
        appointment.setReason(appointmentDetails.getReason());
        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Long id) throws NotFoundException  {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        appointmentRepository.delete(appointment);
    }
}