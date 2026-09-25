package com.dental.dao;

import com.dental.model.Appointment;
import java.sql.SQLException;
import java.util.List;

public interface IAppointmentDAO {
	boolean addNewAppointment(Appointment appointment) throws SQLException;
	Appointment getAppointmentByNumber(String number) throws SQLException;
	List<Appointment> getAllAppointments() throws SQLException;
	List<Appointment> getAppointmentByPatientName(String name) throws SQLException;
	List<Appointment> getAppointmentByDate(String fromDate, String toDate) throws SQLException;
	boolean updateAppointment(Appointment appointment) throws SQLException;
	boolean deleteAppointment(String number) throws SQLException;
	boolean existingAppointment(String number) throws SQLException;
}
