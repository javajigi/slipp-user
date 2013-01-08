package net.slipp.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Properties;

import javax.naming.ConfigurationException;

import net.slipp.dao.user.UserDao;
import net.slipp.support.jdbc.ConnectionManager;
import net.slipp.support.jdbc.H2ConnectionManager;
import net.slipp.support.jdbc.MySQLConnectionManager;

import org.yaml.snakeyaml.Yaml;

public class DaoFactory {
	public static UserDao getUserDao() throws FileNotFoundException, ConfigurationException {
		Map<String, String> config = getDatabaseConfig();
		if(config == null)
			throw new ConfigurationException("해당 config 없음요!");
		
		String adapter = (String)config.get("adapter");
		ConnectionManager connectionManager = null;
		if(adapter.equals("h2")) 
			connectionManager = new H2ConnectionManager(config);
		else if(adapter.equals("mysql"))
			connectionManager = new MySQLConnectionManager(config);
		
		return new UserDao(connectionManager);
	}
	
	private static Map<String, String> getDatabaseConfig() throws FileNotFoundException {
		String environment = System.getProperty("MYENVIRONMENT","development");
		
		Yaml yaml = new Yaml();
		FileInputStream input = new FileInputStream("src/main/resources/database.yml");

		Map map = (Map)yaml.load(input);
		return (Map<String, String>)map.get(environment);
	}
}
