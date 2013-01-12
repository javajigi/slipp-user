package net.slipp.support.jdbc;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionManager {
	public Connection getConnection() throws SQLException, PropertyVetoException;
}
