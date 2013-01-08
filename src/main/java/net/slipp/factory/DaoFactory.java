package net.slipp.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

import javax.naming.ConfigurationException;

import net.slipp.dao.user.UserDao;
import net.slipp.support.jdbc.ConnectionManager;
import net.slipp.support.jdbc.H2ConnectionManagerImpl;
import net.slipp.support.jdbc.MySQLConnectionManagerImpl;

import org.yaml.snakeyaml.Yaml;

public class DaoFactory {
	public static UserDao getUserDao() throws FileNotFoundException, ConfigurationException {
		Map<String, Object> config = getDatabaseConfig();
		if(config == null)
			throw new ConfigurationException("해당 config 없음요!");
		
		ConnectionManager connectionManager = getConnectionManager(config);
		
		return new UserDao(connectionManager);
	}
	
	private static Map<String, Object> getDatabaseConfig() throws FileNotFoundException {
		String environment = System.getProperty("MYENVIRONMENT","development");
		
		Yaml yaml = new Yaml();
		FileInputStream input = new FileInputStream("src/main/resources/database.yml");

		Map map = (Map)yaml.load(input);
		return (Map<String, Object>)map.get(environment);
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
