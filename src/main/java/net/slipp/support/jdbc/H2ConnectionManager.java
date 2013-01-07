package net.slipp.support.jdbc;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;

public class H2ConnectionManager extends ConnectionManager{
	public DataSource getDataSource() throws SQLException{
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:~/slipp-user");
        dataSource.setUsername("sa");
        DatabasePopulatorUtils.execute(databasePopulator(), dataSource);
        return dataSource;
    }
}
