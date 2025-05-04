package com.HospitalBooking.HospitalBooking.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Medical_Recods")
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private String history;
    private String allergies;
    private String medications;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}
	/**
	 * @return the history
	 */
	public String getHistory() {
		return history;
	}
	/**
	 * @return the allergies
	 */
	public String getAllergies() {
		return allergies;
	}
	/**
	 * @return the medications
	 */
	public String getMedications() {
		return medications;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @param patient the patient to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	/**
	 * @param history the history to set
	 */
	public void setHistory(String history) {
		this.history = history;
	}
	/**
	 * @param allergies the allergies to set
	 */
	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}
	/**
	 * @param medications the medications to set
	 */
	public void setMedications(String medications) {
		this.medications = medications;
	}
	@Override
	public String toString() {
		return "MedicalRecord [id=" + id + ", patient=" + patient + ", history=" + history + ", allergies=" + allergies
				+ ", medications=" + medications + "]";
	}
	public MedicalRecord() {
		super();
		
	}

   
}