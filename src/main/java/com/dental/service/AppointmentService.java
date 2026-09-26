package com.dental.service;

import com.dental.dao.AppointmentDAO;
import com.dental.model.Appointment;

import java.sql.SQLException;
import java.util.List;

public class AppointmentService {
	private final AppointmentDAO appointmentDAO = new AppointmentDAO();
	
	public boolean registerAppointment(Appointment appointment) throws SQLException {
		if(appointment.getAppointmentNumber() == null || appointment.getAppointmentNumber().trim().isEmpty()) {
			throw new IllegalArgumentException("Appointment number is required!");
		}
		
		if(appointment.getPatientName() == null || appointment.getPatientName().trim().isEmpty()) {
			throw new IllegalArgumentException("Patient name is required!");
		}
		
		if(appointmentDAO.existingAppointment(appointment.getAppointmentNumber())) {
			throw new IllegalArgumentException("Appointment number already exists!");
		}
		
		return appointmentDAO.addNewAppointment(appointment);
		
	}
	
	public Appointment findAppointment(String appointmentNumber) throws SQLException {
		if(appointmentNumber == null || appointmentNumber.trim().isEmpty()) {
			throw new IllegalArgumentException("Appointment number is required!");
		}
		
		return appointmentDAO.getAppointmentByNumber(appointmentNumber.trim());
	}
	
	public List<Appointment> findAppointment(String patientName, boolean searchByName) throws SQLException {
		if(patientName == null || patientName.trim().isEmpty()) {
			throw new IllegalArgumentException("Patient name is required!");
		}
		
		return appointmentDAO.getAppointmentByPatientName(patientName.trim());
	}
	
	public List<Appointment> findAppointment(String fromDate, String toDate) throws SQLException {
		if(fromDate == null || toDate == null) {
			throw new IllegalArgumentException("Date Range is required!");
		}
		
		return appointmentDAO.getAppointmentByDate(fromDate, toDate);
	}
	
	public List<Appointment> getAllAppointments() throws SQLException {
        return appointmentDAO.getAllAppointments();
    }
	
	public boolean updateAppointment(Appointment appointment) throws SQLException {
		if(!appointmentDAO.existingAppointment(appointment.getAppointmentNumber())) {
			throw new IllegalArgumentException("Appointment not found!");
		}
		
		return appointmentDAO.updateAppointment(appointment);
	}
	
	public boolean deleteAppointment(String appointmentNumber) throws SQLException {
		if(!appointmentDAO.existingAppointment(appointmentNumber)) {
			throw new IllegalArgumentException("Appointment not found!");
		}
		
		return appointmentDAO.deleteAppointment(appointmentNumber);
	}
	
	
	
}
