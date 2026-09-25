package com.dental.dao;

import com.dental.model.Bill;
import java.sql.SQLException;

public interface IBillingDAO {
	boolean saveBill(Bill bill) throws SQLException;
	Bill getBillByAppointmentNumber(String appointmentNumber) throws SQLException;
}
