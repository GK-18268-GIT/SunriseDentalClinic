package com.dental.dao;

import com.dental.model.Bill;
import com.dental.util.DBConnection;

import java.sql.SQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BillingDAO implements IBillingDAO  {
	private Connection getConnection() throws SQLException {
		return DBConnection.getInstance().getConnection();
	}
	
	@Override
	public boolean saveBill(Bill bill) throws SQLException {
		String sql = "INSERT INTO billing(appointment_number, consultation_fee,"
				+ "treatment_cost, total_amount) VALUES (?, ?, ?, ?)";
		try(PreparedStatement ps = getConnection().prepareStatement(sql)) {
			ps.setString(1, bill.getAppointmentNumber());
			ps.setDouble(2, bill.getConsultationFee());
			ps.setDouble(3, bill.getTreatmentCost());
			ps.setDouble(4, bill.getTotalAmount());
			return ps.executeUpdate() > 0;
		}
	}
	
	@Override
	public Bill getBillByAppointmentNumber(String appointmentNumber) throws SQLException {
		String sql = "SELECT * FROM billing WHERE appointment_number = ?";
		try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, appointmentNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Bill bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setAppointmentNumber(rs.getString("appointment_number"));
                bill.setConsultationFee(rs.getDouble("consultation_fee"));
                bill.setTreatmentCost(rs.getDouble("treatment_cost"));
                bill.setTotalAmount(rs.getDouble("total_amount"));
                return bill;
            }
        }
		return null;
	}

}
