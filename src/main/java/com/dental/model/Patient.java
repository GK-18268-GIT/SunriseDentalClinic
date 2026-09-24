package com.dental.model;

public class Patient extends Person {
	public String patientId;
	
	public Patient() {
		
	}
	
	public Patient(String patientId, String name, String contactNumber, String address, int age) {
		super(name, contactNumber, address, age);
		this.patientId = patientId;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	@Override
	public String getRole() {
		return "Patient";
	}
	
}
