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
	private  static final String QUERY_TYPE_DELETE ="DELETE";
	private  static final String QUERY_TYPE_SELECT ="SELECT";
	private  static final String QUERY_TYPE_INSET ="INSERT";
	
	public UserDaoImpl (ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}
	
	public void insert(User user) throws SQLException, PropertyVetoException {
		String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
		execute(user, sql, QUERY_TYPE_INSET);
	}

	
	private User execute(User user,String sql, String queryType) throws SQLException,
	            PropertyVetoException {
	         
	        Connection con = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	         
	        try {
	            con = connectionManager.getConnection();
	             
	            pstmt = con.prepareStatement(sql);
	            if(!QUERY_TYPE_DELETE.equals(queryType)){
	                setPreparedStatement(user, pstmt, queryType);
	            }
	            if(QUERY_TYPE_SELECT.equals(queryType)){
	                rs = pstmt.executeQuery();
	                return setResultSet(rs);
	            }else{
	                pstmt.executeUpdate();
	                return null;
	            }
	        } finally {
	            close(con, pstmt);
	            if (rs != null) {
	            	rs.close();
	    		}
	        }
	    }
	    private User setResultSet(ResultSet rs) throws SQLException {
	        User rsUser = null;
	        if (rs.next()) {
	            rsUser = new User(
	                    rs.getString("userId"), 
	                    rs.getString("password"), 
	                    rs.getString("name"),
	                    rs.getString("email"));
	        }
	        return rsUser;
	    }
	    private void setPreparedStatement(User user, PreparedStatement pstmt, String queryType)
	            throws SQLException {       
	         
	        pstmt.setString(1, user.getUserId());
	        if(QUERY_TYPE_INSET.equals(queryType)){
	            pstmt.setString(2, user.getPassword());
	            pstmt.setString(3, user.getName());
	            pstmt.setString(4, user.getEmail());
	        }
	    }

	public User findByUserId(String userId) throws SQLException, PropertyVetoException {
		
			String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
			User user = new User(userId,"","","");
			return execute(user,sql,QUERY_TYPE_SELECT);
	}

	public void deleteAllUser() throws SQLException, PropertyVetoException {
		String sql = "DELETE FROM USERS";
		execute(null,sql,QUERY_TYPE_DELETE);
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
