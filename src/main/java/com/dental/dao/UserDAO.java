package com.dental.dao;

import com.dental.model.User;
import com.dental.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO implements IUserDAO {
	private Connection getConnection() throws SQLException {
		return DBConnection.getInstance().getConnection();
	}
	
	@Override
	public User getUserByUsername(String username) throws SQLException {
		String sql = "SELECT * FROM users WHERE username = ?";
		try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				return user;
			}
		}
		return null;
	}
	
	@Override
	public boolean validateUser(String username, String password) throws SQLException {
		User user = getUserByUsername(username);
		return user != null && user.getPassword().equals(password);
	}

}
