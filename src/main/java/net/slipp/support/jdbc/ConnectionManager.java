package net.slipp.support.jdbc;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

public abstract class ConnectionManager {
	private DataSource dataSource;
	
	protected String adapter;
	protected String connectionpool;
	protected String protocol;
	protected String database;
	protected String username;
	protected String password;
	protected String host;
	protected String port;
	
	public ConnectionManager(Map<String, String> config) {
		adapter = config.get("adapter");
		connectionpool = config.get("connectionpool");
		protocol = config.get("protocol");
		database = config.get("database");
		username = config.get("username");
		password = config.get("password");
		host = config.get("host");
	}
	
	public Connection getConnection() throws SQLException, PropertyVetoException {
    	if (dataSource == null) {
    		dataSource = getDataSource();
    	}
        return dataSource.getConnection();
    }

    public abstract DataSource getDataSource() throws SQLException, PropertyVetoException;
    
	protected DatabasePopulator databasePopulator() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("user.sql"));
		return populator;
	}
}
