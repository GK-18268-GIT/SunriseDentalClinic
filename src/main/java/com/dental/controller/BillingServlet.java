package com.dental.controller;

import com.dental.model.Appointment;
import com.dental.model.Bill;
import com.dental.service.AppointmentService;
import com.dental.service.BillingService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/BillingServlet")
public class BillingServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final BillingService billingService = new BillingService();
	private final AppointmentService appointmentService = new AppointmentService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!isLoggedIn(request)) {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}
		
		String action = request.getParameter("action");
		try {
			
			if(action == null) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing action parameter");
        		return;
			}
			
			switch(action) {
				case "show":
					request.setAttribute("treatmentCosts", billingService.getTreatmentCosts());
					request.setAttribute("consultationFee", billingService.getConsultationFee());
					request.getRequestDispatcher("/billing.jsp").forward(request, response);
					break;
				
				case "calculate":
					
					String appointmentNumber = request.getParameter("appointmentNumber");
					Bill bill = billingService.calculateBill(appointmentNumber);
					Appointment appointment = appointmentService.findAppointment(appointmentNumber);
					
					request.setAttribute("bill", bill);
					request.setAttribute("appointment", appointment);
					request.setAttribute("treatmentCosts", billingService.getTreatmentCosts());
					request.setAttribute("consultationFee", billingService.getConsultationFee());
					request.getRequestDispatcher("/billing.jsp").forward(request, response);
					break;
					
				default:
                    response.sendRedirect(request.getContextPath() + "/billing.jsp");
			}
		} catch (SQLException e) {
            request.setAttribute("error", "Database error: " + e.getMessage());
            request.getRequestDispatcher("/billing.jsp").forward(request, response);
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/billing.jsp").forward(request, response);
        }
	}
	
	private boolean isLoggedIn(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return session != null && session.getAttribute("username") != null;
	}

}
