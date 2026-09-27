package com.dental.controller;

import com.dental.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final UserService userService = new UserService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		try {
			if(userService.login(username, password)) {
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				session.setMaxInactiveInterval(30 * 60);
				
				response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
				
			} else {
				request.setAttribute("error", "Invalid username or password!");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		} catch(SQLException e) {
			request.setAttribute("error", "Database error. Please try again");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
				
	}
	
	
}
