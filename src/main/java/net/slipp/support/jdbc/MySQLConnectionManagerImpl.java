package net.slipp.support.jdbc;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class MySQLConnectionManagerImpl extends ConnectionManagerImpl{
	private static final String MYSQL_DRIVER_NAME = "com.mysql.jdbc.Driver";
	
	public MySQLConnectionManagerImpl(Map<String, Object> config) {
		super(config);
	}

	protected DataSource getDataSource() throws SQLException, PropertyVetoException{
		DataSource dataSource = null;
		if(connectionpool.equals("dhcp")) {
			dataSource = new BasicDataSource();
			((BasicDataSource)dataSource).setDriverClassName(MYSQL_DRIVER_NAME);
			((BasicDataSource)dataSource).setUrl(makeUrl());
			((BasicDataSource)dataSource).setUsername(username);
			((BasicDataSource)dataSource).setPassword(password);
		}else if(connectionpool.equals("c3p0")) {
			dataSource = new ComboPooledDataSource();
			((ComboPooledDataSource)dataSource).setDriverClass(MYSQL_DRIVER_NAME);            
			((ComboPooledDataSource)dataSource).setJdbcUrl(makeUrl());
			((ComboPooledDataSource)dataSource).setUser(username);
		}
		
		DatabasePopulatorUtils.execute(databasePopulator(), dataSource);
		
		return dataSource;
    }
	
	private String makeUrl() {
		String result = "";
		
		if(protocol.equals("jdbc"))
			result += "jdbc:";
		
		result += "mysql://" + host +":"+port+"/"+database;
		return result;
	}
}
