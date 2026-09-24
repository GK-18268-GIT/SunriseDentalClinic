package com.dental.model;

public class Appointment {
	private String appointmentNumber; 
	private String patientName;
	private String address;
	private int age;
	private String contactNumber;
	private String dentistName;
	private String treatmentType;
	private String appointmentDate;
	private String appointmentTime;
	
	public Appointment() {
		
	}
	
	public Appointment(String appointmentNumber, String patientName, String address, int age, String contactNumber, String dentistName,
						String treatmentType, String appointmentDate, String appointmentTime) {
		this.appointmentNumber = appointmentNumber;
		this.patientName = patientName;
		this.address = address;
		this.age = age;
		this.contactNumber = contactNumber;
		this.dentistName = dentistName;
		this.treatmentType = treatmentType;
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
	}

	public String getAppointmentNumber() {
		return appointmentNumber;
	}

	public void setAppointmentNumber(String appointmentNumber) {
		
		if(appointmentNumber == null || appointmentNumber.trim().isEmpty()) {
			throw new IllegalArgumentException("Appointment number cannot be empty");
		}
		
		this.appointmentNumber = appointmentNumber;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		
		if(patientName == null || patientName.trim().isEmpty()) {
			throw new IllegalArgumentException("Patient name cannot be empty");
		}
		
		this.patientName = patientName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getDentistName() {
		return dentistName;
	}

	public void setDentistName(String dentistName) {
		this.dentistName = dentistName;
	}

	public String getTreatmentType() {
		return treatmentType;
	}

	public void setTreatmentType(String treatmentType) {
		this.treatmentType = treatmentType;
	}

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	
	@Override
    public String toString() {
        return "Appointment{" +
               "number='"   + appointmentNumber + '\'' +
               ", patient='" + patientName       + '\'' +
               ", dentist='" + dentistName       + '\'' +
               ", treatment='" + treatmentType   + '\'' +
               ", date='"   + appointmentDate    + '\'' +
               ", time='"   + appointmentTime    + '\'' +
               '}';
    }
	
	
}
