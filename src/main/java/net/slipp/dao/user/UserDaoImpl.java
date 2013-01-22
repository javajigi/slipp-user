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
		JdbcTemplate template = new JdbcTemplate(connectionManager){
			void setPreparedStatement(PreparedStatement pstmt) throws SQLException{
				pstmt.setString(1, user.getUserId());
	            pstmt.setString(2, user.getPassword());
	            pstmt.setString(3, user.getName());
	            pstmt.setString(4, user.getEmail());
			}
			User setResultSet(ResultSet rs) throws SQLException{
				return null;
			}
		};
		String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
		template.update(user, sql);
	}

	public User findByUserId(final String userId) throws SQLException, PropertyVetoException {
		JdbcTemplate template = new JdbcTemplate(connectionManager){
			void setPreparedStatement(PreparedStatement pstmt) throws SQLException{
				pstmt.setString(1,userId);
			}
			User setResultSet(ResultSet rs) throws SQLException{
				User rsUser = new User(
		                    rs.getString("userId"), 
		                    rs.getString("password"), 
		                    rs.getString("name"),
		                    rs.getString("email"));
		        return rsUser;
			}
		};
		String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
		User user = new User(userId,"","","");
		return template.select(user,sql);
	}

	public void deleteAllUser() throws SQLException, PropertyVetoException {
		JdbcTemplate template = new JdbcTemplate(connectionManager){
			void setPreparedStatement(PreparedStatement pstmt) throws SQLException{
			}
			User setResultSet(ResultSet rs) throws SQLException{
				return null;
			}
		};
		String sql = "DELETE FROM USERS";
		template.update(null,sql);
	}

	public Connection getConnection() throws SQLException, PropertyVetoException {
		return connectionManager.getConnection();
	}
}
