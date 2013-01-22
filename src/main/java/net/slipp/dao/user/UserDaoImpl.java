package net.slipp.dao.user;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.slipp.domain.user.User;
import net.slipp.support.jdbc.ConnectionManager;

public class UserDaoImpl implements UserDao{
	ConnectionManager connectionManager;
	
	public UserDaoImpl (ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}
	
	public void insert(User user) throws SQLException, PropertyVetoException {
		JdbcTemplate template = new JdbcTemplate(connectionManager);
		String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
		template.execute(user, sql, "INSERT");
	}

	public User findByUserId(String userId) throws SQLException, PropertyVetoException {
		JdbcTemplate template = new JdbcTemplate(connectionManager);
		String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
		User user = new User(userId,"","","");
		return template.execute(user,sql,"SELECT");
	}

	public void deleteAllUser() throws SQLException, PropertyVetoException {
		JdbcTemplate template = new JdbcTemplate(connectionManager);
		String sql = "DELETE FROM USERS";
		template.execute(null,sql,"DELETE");
	}

	public Connection getConnection() throws SQLException, PropertyVetoException {
		return connectionManager.getConnection();
	}
}
