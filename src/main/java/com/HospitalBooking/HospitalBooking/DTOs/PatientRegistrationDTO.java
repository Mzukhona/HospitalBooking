package com.HospitalBooking.HospitalBooking.DTOs;

import java.time.LocalDate;

import com.HospitalBooking.HospitalBooking.model.MedicalRecord;
import com.HospitalBooking.HospitalBooking.model.Patient;

public class PatientRegistrationDTO {
    private String username;
    private String password;
    private String email;
    private String name;
    private String surname;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private Patient.Gender gender;
    private Long serviceProviderId;
    private String idNumber;
    private MedicalRecord medical;
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @return the dateOfBirth
	 */
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	/**
	 * @return the gender
	 */
	public Patient.Gender getGender() {
		return gender;
	}
	/**
	 * @return the serviceProviderId
	 */
	public Long getServiceProviderId() {
		return serviceProviderId;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
	public void setGender(Patient.Gender gender) {
		this.gender = gender;
	}
	/**
	 * @param serviceProviderId the serviceProviderId to set
	 */
	public void setServiceProviderId(Long serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}
	@Override
	public String toString() {
		return "PatientRegistrationDTO [username=" + username + ", password=" + password + ", email=" + email
				+ ", name=" + name + ", surname=" + surname + ", phoneNumber=" + phoneNumber + ", dateOfBirth="
				+ dateOfBirth + ", gender=" + gender + ", serviceProviderId=" + serviceProviderId + "]";
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
	/**
	 * @return the medical
	 */
	public MedicalRecord getMedical() {
		return medical;
	}
	/**
	 * @param medical the medical to set
	 */
	public void setMedical(MedicalRecord medical) {
		this.medical = medical;
	} 

    
}
