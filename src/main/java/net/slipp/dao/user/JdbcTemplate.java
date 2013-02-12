package net.slipp.dao.user;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.slipp.support.jdbc.ConnectionManager;

public class JdbcTemplate {
	private ConnectionManager connectionManager = null;

	public JdbcTemplate(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}

	public <T> T select(String sql, PreparedStatementSetter pss,
			RowMapper<T> rowMapper) throws SQLException, PropertyVetoException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = connectionManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pss.setValue(pstmt);

			rs = pstmt.executeQuery();
			if (rs.next())
				return rowMapper.setResultSet(rs);
			return null;

		} finally {
			close(con, pstmt);
			if (rs != null) {
				rs.close();
			}
		}
	}

	public void update(String sql, PreparedStatementSetter pss)
			throws SQLException, PropertyVetoException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = connectionManager.getConnection();
		
			pstmt = con.prepareStatement(sql);
			if(pss != null)
				pss.setValue(pstmt);
		
			pstmt.executeUpdate();
		
		} finally {
			close(con, pstmt);
			if (rs != null) {
				rs.close();
			}
		}
	}
	
	public void update(String sql) throws SQLException, PropertyVetoException {
		PreparedStatementSetter pss = null;
		update(sql, pss);

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

	public void update(String sql, String...params) throws SQLException, PropertyVetoException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = connectionManager.getConnection();
		
			pstmt = con.prepareStatement(sql);
			
			int length =  params.length;
			for (int i = 0; i <length; i++) {
				pstmt.setString(i+1, params[i]);
			}		
			pstmt.executeUpdate();
		
		} finally {
			close(con, pstmt);
			if (rs != null) {
				rs.close();
			}
		}
	}

	public int queryForInt(String sql) throws SQLException, PropertyVetoException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = connectionManager.getConnection();
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();
			if (rs.next())
				return rs.getInt(1);

		} finally {
			close(con, pstmt);
			if (rs != null) {
				rs.close();
			}
		}
		return 0;
	}

}
