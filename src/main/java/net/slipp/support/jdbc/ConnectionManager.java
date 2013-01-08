package net.slipp.support.jdbc;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public interface ConnectionManager {
	public Connection getConnection() throws SQLException, PropertyVetoException;
    public DataSource getDataSource() throws SQLException, PropertyVetoException;
}
