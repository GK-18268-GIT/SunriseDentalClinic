package com.dental.service;

import com.dental.dao.AppointmentDAO;
import com.dental.dao.BillingDAO;
import com.dental.model.Appointment;
import com.dental.model.Bill;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BillingService {
	private final BillingDAO billingDAO = new BillingDAO();
	private final AppointmentDAO appointmentDAO = new AppointmentDAO();
	
	private static final Map<String, Double> TREATMENT_COSTS;
    static {
        Map<String, Double> map = new HashMap<>();
        map.put("Teeth Cleaning",      2500.0);
        map.put("Tooth Extraction",    3500.0);
        map.put("Root Canal",         15000.0);
        map.put("Teeth Whitening",     8000.0);
        map.put("Dental Filling",      4000.0);
        map.put("Orthodontic Consult", 5000.0);
        map.put("X-Ray",               2000.0);
        TREATMENT_COSTS = Collections.unmodifiableMap(map);
    }
    
    private static final double CONSULTATION_FEE = 1500.0;

    public Bill calculateBill(String appointmentNumber) throws SQLException {
        if (!appointmentDAO.existingAppointment(appointmentNumber)) {
            throw new IllegalArgumentException("Appointment not found.");
        }

        // Return existing bill if already calculated
        Bill existing = billingDAO.getBillByAppointmentNumber(appointmentNumber);
        if (existing != null) return existing;

        Appointment appointment = appointmentDAO.getAppointmentByNumber(appointmentNumber);
        double treatmentCost = TREATMENT_COSTS.getOrDefault(
            appointment.getTreatmentType(), 3000.0
        );

        Bill bill = new Bill(appointmentNumber, CONSULTATION_FEE, treatmentCost);
        billingDAO.saveBill(bill);
        return bill;
    }
    
    public Bill getBill(String appointmentNumber) throws SQLException {
        return billingDAO.getBillByAppointmentNumber(appointmentNumber);
    }

    public Map<String, Double> getTreatmentCosts() {
        return TREATMENT_COSTS;
    }

    public double getConsultationFee() {
        return CONSULTATION_FEE;
    }
	
}
