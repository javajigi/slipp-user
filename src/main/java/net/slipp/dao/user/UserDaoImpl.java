package net.slipp.dao.user;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.slipp.domain.user.User;
import net.slipp.support.jdbc.ConnectionManager;

public class UserDaoImpl implements UserDao{
private ConnectionManager connectionManager = null;
	public UserDaoImpl (ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}
	
	public void insert(User user) throws SQLException, PropertyVetoException {
		String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
		executeUpdate(user, sql);
	}

	private void executeUpdate(User user,String sql) throws SQLException,
			PropertyVetoException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = connectionManager.getConnection();
			
			pstmt = con.prepareStatement(sql);
			if(user != null){
				pstmt.setString(1, user.getUserId());
				pstmt.setString(2, user.getPassword());
				pstmt.setString(3, user.getName());
				pstmt.setString(4, user.getEmail());
			}
			pstmt.executeUpdate();
		} finally {
			close(con, pstmt);
		}
	}

	public User findByUserId(String userId) throws SQLException, PropertyVetoException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = connectionManager.getConnection();
			String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);

			rs = pstmt.executeQuery();

			User user = null;
			if (rs.next()) {
				user = new User(
						rs.getString("userId"), 
						rs.getString("password"), 
						rs.getString("name"),
						rs.getString("email"));
			}

			return user;
		} finally {
			if (rs != null) {
				rs.close();
			}
			close(con, pstmt);
		}
	}

	public void deleteAllUser() throws SQLException, PropertyVetoException {
		String sql = "DELETE FROM USERS";
		executeUpdate(null,sql);
	}

	private void close(Connection con, PreparedStatement pstmt)
			throws SQLException {
		if (pstmt != null) {
			pstmt.close();
		}
		if (con != null) {
			con.close();
		}
	}

	public Connection getConnection() throws SQLException, PropertyVetoException {
		return connectionManager.getConnection();
	}
}
