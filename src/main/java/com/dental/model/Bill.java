package com.dental.model;

public class Bill {
	private int id;
	private String appointmentNumber;
	private double consultationFee;
	private double treatmentCost;
	private double totalAmount;
	
	public Bill() {
		
	}
	
	public Bill(String appointmentNumber, double consultationFee, double treatmentCost) {
	    this.appointmentNumber = appointmentNumber;
	    this.consultationFee   = consultationFee;
	    this.treatmentCost     = treatmentCost;
	    this.totalAmount       = consultationFee + treatmentCost;
	}
	
	public Bill(int id, String appointmentNumber, double consultationFee, double treatmentCost, double totalAmount) {
		this.id = id;
		this.appointmentNumber = appointmentNumber;
		this.consultationFee = consultationFee;
		this.treatmentCost = treatmentCost;
		this.totalAmount = totalAmount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAppointmentNumber() {
		return appointmentNumber;
	}

	public void setAppointmentNumber(String appointmentNumber) {
		this.appointmentNumber = appointmentNumber;
	}

	public double getConsultationFee() {
		return consultationFee;
	}

	public void setConsultationFee(double consultationFee) {
		this.consultationFee = consultationFee;
	}

	public double getTreatmentCost() {
		return treatmentCost;
	}

	public void setTreatmentCost(double treatmentCost) {
		this.treatmentCost = treatmentCost;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	@Override
    public String toString() {
        return "Bill{" +
               "appointment='" + appointmentNumber + '\'' +
               ", consultation=" + consultationFee +
               ", treatment="    + treatmentCost   +
               ", total="        + totalAmount      +
               '}';
    }
}
