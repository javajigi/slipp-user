package net.slipp.support.jdbc;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

public abstract class ConnectionManagerImpl implements ConnectionManager{
	private DataSource dataSource;
	
	protected String adapter;
	protected String connectionpool;
	protected String protocol;
	protected String database;
	protected String username;
	protected String password;
	protected String host;
	protected String port;
	
	public ConnectionManagerImpl(Map<String, Object> config) {
		adapter = (String)config.get("adapter");
		connectionpool = (String)config.get("connectionpool");
		protocol = (String)config.get("protocol");
		database = (String)config.get("database");
		username = (String)config.get("username");
		password = (String)config.get("password");
		host = (String)config.get("host");
		port = String.valueOf(config.get("port"));
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
