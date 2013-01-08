package net.slipp.support.jdbc;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class H2ConnectionManagerImpl extends ConnectionManagerImpl{
	private static final String H2_DRIVER_NAME = "org.h2.Driver";
	
	public H2ConnectionManagerImpl(Map<String,Object> config) {
		super(config);
	}

	public DataSource getDataSource() throws SQLException, PropertyVetoException{
		DataSource dataSource = null;
		if(connectionpool.equals("dhcp")) {
			dataSource = new BasicDataSource();
			((BasicDataSource)dataSource).setDriverClassName(H2_DRIVER_NAME);
			((BasicDataSource)dataSource).setUrl(makeUrl());
			((BasicDataSource)dataSource).setUsername(username);
		}else if(connectionpool.equals("c3p0")) {
			dataSource = new ComboPooledDataSource();
			((ComboPooledDataSource)dataSource).setDriverClass(H2_DRIVER_NAME);            
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

		result += "h2:~/" + database;
		return result;
	}
}
