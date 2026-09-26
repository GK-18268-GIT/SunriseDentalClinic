package com.dental.dao;

import com.dental.model.Appointment;
import com.dental.util.DBConnection;

import java.sql.SQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO implements IAppointmentDAO {
	private Connection getConnection() throws SQLException {
		return DBConnection.getInstance().getConnection();
	}
	
	@Override
	public boolean addNewAppointment(Appointment appointment) throws SQLException{
		String sql = "INSERT INTO appointments (appointment_number, patient_name, address, age"
				+ "contact_number, dentist_name, treatment_type, appointment_date, appointment_time)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try(PreparedStatement ps = getConnection().prepareStatement(sql)) {
			ps.setString(1, appointment.getAppointmentNumber());
			ps.setString(2, appointment.getPatientName());
			ps.setString(3, appointment.getAddress());
			ps.setInt(4, appointment.getAge());
			ps.setString(5, appointment.getContactNumber());
			ps.setString(6, appointment.getDentistName());
			ps.setString(7, appointment.getTreatmentType());
			ps.setString(8, appointment.getAppointmentTime());
			ps.setString(9, appointment.getAppointmentDate());
			
			return ps.executeUpdate() > 0;
		}
		
	}
	
	@Override
	public Appointment getAppointmentByNumber(String appointmentNumber) throws SQLException{
		String sql = "SELECT * FROM appointments WHERE appointment_number = ?";
		try(PreparedStatement ps = getConnection().prepareStatement(sql)) {
			ps.setString(1, appointmentNumber);
			ResultSet rs = ps.executeQuery(sql);
			if(rs.next()) return mapResultSet(rs);
		}
		return null;
	}
	
	@Override
	public List<Appointment> getAllAppointments() throws SQLException {
		List<Appointment> list = new ArrayList<>();
		String sql = "SELECT * FROM appointments ORDER BY appointment_date, appointment_time";
		try(PreparedStatement ps = getConnection().prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) list.add(mapResultSet(rs));
		}
		return list;
	}
	
	@Override
	public List<Appointment> getAppointmentByPatientName(String name) throws SQLException {
		List<Appointment> list = new ArrayList<>();
		String sql = "SELECT * FROM appointemtns WHERE patient_name LIKE ?";
		try(PreparedStatement ps = getConnection().prepareStatement(sql)) {
			ps.setString(1, "%" +  name + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) list.add(mapResultSet(rs));
		}
		return list;
	}
	
	@Override
	public List<Appointment> getAppointmentByDate(String fromDate, String toDate) throws SQLException {
		List<Appointment> list = new ArrayList<>();
		String sql = "SELECT * FROM appointment WHERE appointment_date BETWEEN ? AND ? ORDER BY appointment_date, appointment_time";
		try(PreparedStatement ps = getConnection().prepareStatement(sql)) {
			ps.setString(1, fromDate);
			ps.setString(2, toDate);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) list.add(mapResultSet(rs));
		}
		return list;
	}
	
	@Override
	public boolean updateAppointment(Appointment appointment) throws SQLException {
		String sql = "UPDATE appointments SET patient_name = ?, address = ?, age = ?, contact_number = ?, dentist_name = ?, "
				+ "treatment_type = ?, appointment_date = ?, appointment_time = ? WHERE appointment_number = ?";
		try(PreparedStatement ps = getConnection().prepareStatement(sql)) {
			ps.setString(1, appointment.getPatientName());
			ps.setString(2, appointment.getAddress());
			ps.setInt(3, appointment.getAge());
			ps.setString(4, appointment.getContactNumber());
			ps.setString(5, appointment.getDentistName());
			ps.setString(6, appointment.getTreatmentType());
			ps.setString(7, appointment.getAppointmentTime());
			ps.setString(8, appointment.getAppointmentDate());
			ps.setString(9, appointment.getAppointmentNumber());
			return ps.executeUpdate() > 0;
		}
	}
	
	@Override
	public boolean deleteAppointment(String appointmentNumber) throws SQLException {
		String sql = "DELETE FROM appointments WHERE appointment_number = ?";
		try(PreparedStatement ps = getConnection().prepareStatement(sql)) {
			ps.setString(1, appointmentNumber);
			return ps.executeUpdate() > 0;
		}
	}
	
	@Override
	public boolean existingAppointment(String appointmentNumber) throws SQLException {
		String sql = "SELECT 1 FROM appointments WHERE appoint_number = ?";
		try(PreparedStatement ps = getConnection().prepareStatement(sql)) {
			ps.setString(1, appointmentNumber);
			return ps.executeQuery().next();
		}
	}
	
	private Appointment mapResultSet(ResultSet rs) throws SQLException {
        Appointment a = new Appointment();
        a.setAppointmentNumber(rs.getString("appointment_number"));
        a.setPatientName(rs.getString("patient_name"));
        a.setAddress(rs.getString("address"));
        a.setContactNumber(rs.getString("contact_number"));
        a.setDentistName(rs.getString("dentist_name"));
        a.setTreatmentType(rs.getString("treatment_type"));
        a.setAppointmentDate(rs.getString("appointment_date"));
        a.setAppointmentTime(rs.getString("appointment_time"));
        return a;
    }
	
}
