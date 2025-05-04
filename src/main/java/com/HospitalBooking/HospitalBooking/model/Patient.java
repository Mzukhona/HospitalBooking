package com.HospitalBooking.HospitalBooking.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name = "user_id")
@Table(name = "Patient")
public class Patient extends User {
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LocalDate dateOfBirth;
	private String idNumber;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne(optional = false)
    @JoinColumn(name = "service_provider_id")
    private ServiceProvider serviceProvider;

    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)
    private MedicalRecord medicalRecord;

    @OneToMany(mappedBy = "patient")
    private Set<Appointment> appointments = new HashSet<>();

	public Patient(Long id, String name, String surname, String email, String username, String password,
			String phoneNumber, LocalDate dateOfBirth,String idNumber, Gender gender, ServiceProvider serviceProvider,
			MedicalRecord medicalRecord, Set<Appointment> appointments) {
		super(id, name, surname, email, username, password, phoneNumber);
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.idNumber = idNumber;
		this.serviceProvider = serviceProvider;
		this.medicalRecord = medicalRecord;
		this.appointments = appointments;
	}
	public Patient() {}

	/**
	 * @return the dateOfBirth
	 */
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * @return the serviceProvider
	 */
	public ServiceProvider getServiceProvider() {
		return serviceProvider;
	}

	/**
	 * @return the medicalRecord
	 */
	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}

	/**
	 * @return the appointments
	 */
	public Set<Appointment> getAppointments() {
		return appointments;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * @param serviceProvider the serviceProvider to set
	 */
	public void setServiceProvider(ServiceProvider serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	/**
	 * @param medicalRecord the medicalRecord to set
	 */
	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	/**
	 * @param appointments the appointments to set
	 */
	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}

	public enum Gender {
		MALE, FEMALE, OTHER
	}

	/**
	 * @return the idNumber
	 */
	public String getIdNumber() {
		return idNumber;
	}
	/**
	 * @param idNumber the idNumber to set
	 */
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
}
