package com.dental.model;

public class Dentist extends Person {
	private String specialization;
	
	public Dentist() {
		
	}
	
	public Dentist(String specialization, String name, String contactNumber, String address, int age) {
		super(name, contactNumber, address, age);
		this.specialization = specialization;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	
	@Override
	public String getRole() {
		return "Dentist";
	}
	
}
