package com.dental.service;

import com.dental.dao.UserDAO;
import java.sql.SQLException;

public class UserService {
	private final UserDAO userDAO = new UserDAO();
	
	public boolean login(String username, String password) throws SQLException {
		if(username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
			return false;
		}
		return userDAO.validateUser(username.trim(), password);
	}
}
