package net.slipp.dao.user;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.slipp.domain.user.User;
import net.slipp.support.jdbc.ConnectionManager;

public class JdbcTemplate {
	private ConnectionManager connectionManager = null;
	private  static final String QUERY_TYPE_DELETE ="DELETE";
	private  static final String QUERY_TYPE_SELECT ="SELECT";
	private  static final String QUERY_TYPE_INSET ="INSERT";
	
	public JdbcTemplate(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}
	
	public User execute(User user,String sql, String queryType) throws SQLException,
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
	    
	    private void close(Connection con, PreparedStatement pstmt)
				throws SQLException {
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
}
