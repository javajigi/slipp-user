package net.slipp.support.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

public abstract class ConnectionManager {
	private DataSource dataSource;
	
    public Connection getConnection() throws SQLException {
    	if (dataSource == null) {
    		dataSource = getDataSource();
    	}
        return dataSource.getConnection();
    }

    private DataSource getDataSource() throws SQLException {
        throw new SQLException("DataSource is not implemented!");
    }
    
	protected DatabasePopulator databasePopulator() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("user.sql"));
		return populator;
	}
}
