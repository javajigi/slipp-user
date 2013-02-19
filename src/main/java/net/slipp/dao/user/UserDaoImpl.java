package net.slipp.dao.user;

import java.beans.PropertyVetoException;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.naming.ConfigurationException;

import org.springframework.stereotype.Repository;

import net.slipp.domain.user.User;
import net.slipp.support.ConfigManager;
import net.slipp.support.jdbc.ConnectionManager;
import net.slipp.support.jdbc.H2ConnectionManagerImpl;
import net.slipp.support.jdbc.MySQLConnectionManagerImpl;

@Repository("userDao")
public class UserDaoImpl implements UserDao{
	ConnectionManager connectionManager;
	
	public UserDaoImpl() throws FileNotFoundException, ConfigurationException{
		Map<String, Object> config = ConfigManager.getDatabaseConfig();
		if(config == null)
			throw new ConfigurationException("해당 config 없음요!");
		connectionManager = getConnectionManager(config);
	}
	
	private static ConnectionManager getConnectionManager(Map<String, Object> config){
		ConnectionManager connectionManager = null;
		String adapter = (String)config.get("adapter");
		if(adapter.equals("h2"))
			connectionManager = new H2ConnectionManagerImpl(config);
		else if(adapter.equals("mysql"))
			connectionManager = new MySQLConnectionManagerImpl(config);
		return connectionManager;
	}
	
	public void insert(final User user) throws SQLException, PropertyVetoException {
		JdbcTemplate template = new JdbcTemplate(connectionManager);
		
		String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
		template.update(sql,user.getUserId(),user.getPassword(),user.getName(),user.getEmail());
	}
	
	@Override
    public void update(User user) throws SQLException, PropertyVetoException {
        JdbcTemplate template = new JdbcTemplate(connectionManager);
        
        String sql = "UPDATE USERS SET name=?, email=? WHERE userId = ?";
        template.update(sql,user.getName(),user.getEmail(),user.getUserId());
    }

	public User findByUserId(final String userId) throws SQLException, PropertyVetoException {
		JdbcTemplate template = new JdbcTemplate(connectionManager);
		
		PreparedStatementSetter pss = new PreparedStatementSetter(){
			public void setValue(PreparedStatement pstmt) throws SQLException{
				pstmt.setString(1, userId);
			}
		};
		
		RowMapper<User> rowMapper = new RowMapper<User>(){
			@Override
			public User setResultSet(ResultSet rs) throws SQLException {
				return new User(
	                    rs.getString("userId"), 
	                    rs.getString("password"), 
	                    rs.getString("name"),
	                    rs.getString("email"));
			};
		};
		String sql = "SELECT userId, password, name, email FROM USERS WHERE userId=?";
		return (User)template.select(sql,pss,rowMapper);
	}

	public void deleteAllUser() throws SQLException, PropertyVetoException {
		JdbcTemplate template = new JdbcTemplate(connectionManager);
		
		String sql = "DELETE FROM USERS";
		template.update(sql);
	}

	public Connection getConnection() throws SQLException, PropertyVetoException {
		return connectionManager.getConnection();
	}

	@Override
	public int countUser() throws SQLException, PropertyVetoException {
		JdbcTemplate template = new JdbcTemplate(connectionManager);
		String sql = "select count(*) FROM USERS";
		return template.queryForInt(sql);
	}
}
