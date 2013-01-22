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
	
	public void insert(final User user) throws SQLException, PropertyVetoException {
		JdbcTemplate template = new JdbcTemplate(connectionManager);
		PreparedStatementSetter pss = new PreparedStatementSetter(){
			public void setValue(PreparedStatement pstmt) throws SQLException{
				pstmt.setString(1, user.getUserId());
	            pstmt.setString(2, user.getPassword());
	            pstmt.setString(3, user.getName());
	            pstmt.setString(4, user.getEmail());
			}
		};
		String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
		template.update(sql,pss);
	}

	public User findByUserId(final String userId) throws SQLException, PropertyVetoException {
		JdbcTemplate template = new JdbcTemplate(connectionManager);
		
		PreparedStatementSetter pss = new PreparedStatementSetter(){
			public void setValue(PreparedStatement pstmt) throws SQLException{
				pstmt.setString(1, userId);
			}
		};
		
		RowMapper rowMapper = new RowMapper(){
			@Override
			public User setResultSet(ResultSet rs) throws SQLException {
				return new User(
	                    rs.getString("userId"), 
	                    rs.getString("password"), 
	                    rs.getString("name"),
	                    rs.getString("email"));
			};
		};
		String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
		return (User)template.select(sql,pss,rowMapper);
	}

	public void deleteAllUser() throws SQLException, PropertyVetoException {
		JdbcTemplate template = new JdbcTemplate(connectionManager);
		PreparedStatementSetter pss = new PreparedStatementSetter(){
			public void setValue(PreparedStatement pstmt) throws SQLException{
			}
		};
		String sql = "DELETE FROM USERS";
		template.update(sql,pss);
	}

	public Connection getConnection() throws SQLException, PropertyVetoException {
		return connectionManager.getConnection();
	}
}
