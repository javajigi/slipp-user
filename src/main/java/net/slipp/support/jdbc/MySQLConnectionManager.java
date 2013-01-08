package net.slipp.support.jdbc;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class MySQLConnectionManager extends ConnectionManager{
	public MySQLConnectionManager(Map<String, String> config) {
		super(config);
	}

	public DataSource getDataSource() throws SQLException, PropertyVetoException{
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setDriverClass( "com.mysql.jdbc.Driver" );            
		cpds.setJdbcUrl(makeUrl());
		cpds.setUser(username);                                  
		cpds.setPassword(password);
        return cpds;
    }
	
	private String makeUrl() {
		String result = "";
		
		if(protocol.equals("jdbc"))
			result += "jdbc:";
		
		result += "mysql://" + host +":"+port+"/"+database;
		return result;
	}
}
