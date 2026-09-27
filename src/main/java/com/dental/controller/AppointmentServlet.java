package com.dental.controller;

import com.dental.model.Appointment;
import com.dental.service.AppointmentService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/AppointmentServlet")
public class AppointmentServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
				case "list":
					List<Appointment> appointments = appointmentService.getAllAppointments();
					request.setAttribute("appointments", appointments);
					request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
					break;
				
				case "search":
					String searchNumber = request.getParameter("appointmentNumber");
					Appointment found = appointmentService.findAppointment(searchNumber);
					request.setAttribute("appointment", found);
					if(found == null) {
						request.setAttribute("error", "Appointment not found for: " + searchNumber);
					}
					request.getRequestDispatcher("/view-appointment.jsp").forward(request, response);
					break;
					
				case "searchByName":
					String patientName = request.getParameter("patientName");
					List<Appointment> byName = appointmentService.findAppointment(patientName, true);
					request.setAttribute("appointment", byName);
					request.setAttribute("searchTerm", patientName);
					request.getRequestDispatcher("/view-appointment.jsp").forward(request, response);
					break;
					
				case "searchByDate":
					String fromDate = request.getParameter("fromDate");
					String toDate = request.getParameter("toDate");
					List<Appointment> byDate = appointmentService.findAppointment(fromDate, toDate);
					request.setAttribute("appointment", byDate);
					request.getRequestDispatcher("/view-appointment.jsp").forward(request, response);
					break;
					
				case "edit":
					String editNumber = request.getParameter("appointmentNumber");
					Appointment toEdit = appointmentService.findAppointment(editNumber);
					request.setAttribute("appointment", toEdit);
					if(toEdit == null) {
						request.setAttribute("error", "Appointment not found for: " + editNumber);
					}
					request.getRequestDispatcher("/register-appointment.jsp").forward(request, response);
					break;
					
				case "delete":
				    String deleteNumber = request.getParameter("appointmentNumber");
				    appointmentService.deleteAppointment(deleteNumber);
				    response.sendRedirect(request.getContextPath() + "/AppointmentServlet?action=list&message=Appointment+deleted.");
				    break;
					
				default:
                    response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
			}
			
		} catch(SQLException e) {
			request.setAttribute("error", "Database error: " + e.getMessage());
            request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
		} catch(IllegalArgumentException e) {
			request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/view-appointment.jsp").forward(request, response);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
				case "register":
					Appointment newAppointment = buildAppointmentFromRequest(request);
					appointmentService.registerAppointment(newAppointment);
					response.sendRedirect(request.getContextPath() + "/AppointmentServlet?action=list&message=Appointment+registered.");
					break;
				
				case "update":
					Appointment updateAppointment = buildAppointmentFromRequest(request);
					appointmentService.updateAppointment(updateAppointment);
					response.sendRedirect(request.getContextPath() + "/AppointmentServlet?action=list&message=Appointment+update.");
					break;
				
				default:
	                response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
			
			}
			
		} catch(SQLException e) {
			request.setAttribute("error", "Database error: " + e.getMessage());
            request.getRequestDispatcher("/register-appointment.jsp").forward(request, response);
		} catch(IllegalArgumentException e) {
			request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/register-appointment.jsp").forward(request, response);
		}
	}
	
	private Appointment buildAppointmentFromRequest(HttpServletRequest request) {
        Appointment a = new Appointment();
        a.setAppointmentNumber(request.getParameter("appointmentNumber"));
        a.setPatientName(request.getParameter("patientName"));
        a.setAddress(request.getParameter("address"));
        a.setContactNumber(request.getParameter("contactNumber"));
        a.setDentistName(request.getParameter("dentistName"));
        a.setTreatmentType(request.getParameter("treatmentType"));
        a.setAppointmentDate(request.getParameter("appointmentDate"));
        a.setAppointmentTime(request.getParameter("appointmentTime"));
        return a;
    }
	
	private boolean isLoggedIn(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return session != null && session.getAttribute("username") != null;
	}

}
