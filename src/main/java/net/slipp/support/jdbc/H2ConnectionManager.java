package net.slipp.support.jdbc;

import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;

public class H2ConnectionManager extends ConnectionManager{
	public H2ConnectionManager(Map<String,String> config) {
		super(config);
	}

	public DataSource getDataSource() throws SQLException{
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl(makeUrl());
		dataSource.setUsername(username);
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
