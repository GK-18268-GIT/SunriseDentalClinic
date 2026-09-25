package com.dental.dao;

import com.dental.model.User;
import java.sql.SQLException;

public interface IUserDAO {
	User getUserByUsername(String username) throws SQLException;
	boolean validateUser(String username, String password) throws SQLException;
}
