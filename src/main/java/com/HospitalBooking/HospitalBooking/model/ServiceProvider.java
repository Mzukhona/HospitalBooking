package com.HospitalBooking.HospitalBooking.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name = "user_id")
@Table(name = "ServiceProviders")
public class ServiceProvider extends User {
   
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String specialization;

	@ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @OneToMany(mappedBy = "serviceProvider")
    private Set<Appointment> appointments = new HashSet<>();

    @OneToMany(mappedBy = "serviceProvider")
    private Set<Patient> patients = new HashSet<>();

	public ServiceProvider(Long id, String name, String surname, String email, String username, String password,
			String phoneNumber, String specialization, Hospital hospitals, Set<Appointment> appointments,
			Set<Patient> patients) {
		super(id, name, surname, email, username, password, phoneNumber);
		this.specialization = specialization;
		this.hospital = hospitals;
		this.appointments = appointments;
		this.patients = patients;
	}
	public ServiceProvider() {}

	/**
	 * @return the specialization
	 */
	public String getSpecialization() {
		return specialization;
	}

	/**
	 * @return the hospitals
	 */
	public Hospital getHospitals() {
		return hospital;
	}

	/**
	 * @return the appointments
	 */
	public Set<Appointment> getAppointments() {
		return appointments;
	}

	/**
	 * @return the patients
	 */
	public Set<Patient> getPatients() {
		return patients;
	}

	/**
	 * @param specialization the specialization to set
	 */
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	/**
	 * @param hospitals the hospitals to set
	 */
	public void setHospitals(Hospital hospitals) {
		this.hospital = hospitals;
	}

	/**
	 * @param appointments the appointments to set
	 */
	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}

	/**
	 * @param patients the patients to set
	 */
	public void setPatients(Set<Patient> patients) {
		this.patients = patients;
	}

	@Override
	public String toString() {
		return "ServiceProvider [specialization=" + specialization + ", hospitals=" + hospital + ", appointments="
				+ appointments + ", patients=" + patients + ", name=" + name + ", surname=" + surname + ", email="
				+ email + "]";
	}

    
}

