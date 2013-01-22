package net.slipp.dao.user;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.slipp.domain.user.User;
import net.slipp.support.jdbc.ConnectionManager;

public abstract class JdbcTemplate {
	private ConnectionManager connectionManager = null;
	
	public JdbcTemplate(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}
	
	public User select(User user,String sql) throws SQLException,
    PropertyVetoException {
 
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			 
			try {
			    con = connectionManager.getConnection();
			     
			    pstmt = con.prepareStatement(sql);
			    setPreparedStatement(pstmt);
			    
			    rs = pstmt.executeQuery();
			    if(rs.next())
			    	return setResultSet(rs);
			    return null;
			    
			} finally {
			    close(con, pstmt);
			    if (rs != null) {
			    	rs.close();
				}
			}
		}
	public void update(User user,String sql) throws SQLException,
    PropertyVetoException {
 
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			 
			try {
			    con = connectionManager.getConnection();
			     
			    pstmt = con.prepareStatement(sql);
			    setPreparedStatement(pstmt);
			    
			    pstmt.executeUpdate();
			    
			} finally {
			    close(con, pstmt);
			    if (rs != null) {
			    	rs.close();
				}
			}
		}
	 abstract User setResultSet(ResultSet rs) throws SQLException;
	 
	 abstract void setPreparedStatement( PreparedStatement pstmt)
	            throws SQLException;
	    
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
