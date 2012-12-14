package net.slipp.support.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

public class ConnectionManager {
	private static DataSource dataSource;
	
    public static Connection getConnection() throws SQLException {
    	if (dataSource == null) {
    		dataSource = getDataSource();
    	}
        return dataSource.getConnection();
    }

    public static DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:~/slipp-user");
        dataSource.setUsername("sa");
        DatabasePopulatorUtils.execute(databasePopulator(), dataSource);
        return dataSource;
    }
    
	private static DatabasePopulator databasePopulator() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("user.sql"));
		return populator;
	}
}
