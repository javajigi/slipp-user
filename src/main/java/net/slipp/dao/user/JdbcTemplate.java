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
	
	public JdbcTemplate(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}
	
	public User select(String sql, PreparedStatementSetter pss, RowMapper rowMapper) throws SQLException,
    PropertyVetoException {
 
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			 
			try {
			    con = connectionManager.getConnection();
			    pstmt = con.prepareStatement(sql);
			    pss.setValue(pstmt);
			    
			    rs = pstmt.executeQuery();
			    if(rs.next())
			    	return rowMapper.setResultSet(rs);
			    return null;
			    
			} finally {
			    close(con, pstmt);
			    if (rs != null) {
			    	rs.close();
				}
			}
		}
	public void update(String sql,PreparedStatementSetter pss) throws SQLException,
    PropertyVetoException {
 
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			 
			try {
			    con = connectionManager.getConnection();
			     
			    pstmt = con.prepareStatement(sql);
			    pss.setValue(pstmt);
			    
			    pstmt.executeUpdate();
			    
			} finally {
			    close(con, pstmt);
			    if (rs != null) {
			    	rs.close();
				}
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
