package net.slipp.factory;

import java.io.FileNotFoundException;
import java.util.Map;

import javax.naming.ConfigurationException;

import net.slipp.dao.user.TestUserDaoImpl;
import net.slipp.dao.user.UserDao;
import net.slipp.dao.user.UserDaoImpl;
import net.slipp.support.ConfigManager;
import net.slipp.support.jdbc.ConnectionManager;
import net.slipp.support.jdbc.H2ConnectionManagerImpl;
import net.slipp.support.jdbc.MySQLConnectionManagerImpl;

public class DaoFactory {
    private static ConnectionManager connectionManager;
    
	public static UserDao getUserDao() throws FileNotFoundException, ConfigurationException {
		if(ConfigManager.getEnvironment().equals("test"))
			return new TestUserDaoImpl();
		
		Map<String, Object> config = ConfigManager.getDatabaseConfig();
		if(config == null)
			throw new ConfigurationException("해당 config 없음요!");
		
		if ( connectionManager == null ) {
		    connectionManager = getConnectionManager(config);
		}
		
		return new UserDaoImpl(connectionManager);
	}
	
	private static ConnectionManager getConnectionManager(Map<String, Object> config) {
		ConnectionManager connectionManager = null;
		
		String adapter = (String)config.get("adapter");
		
		if(adapter.equals("h2")) 
			connectionManager = new H2ConnectionManagerImpl(config);
		else if(adapter.equals("mysql"))
			connectionManager = new MySQLConnectionManagerImpl(config);
		
		return connectionManager;
	}
}
