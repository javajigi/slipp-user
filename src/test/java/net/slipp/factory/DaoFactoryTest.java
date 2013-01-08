package net.slipp.factory;

import java.beans.PropertyVetoException;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import javax.naming.ConfigurationException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import net.slipp.dao.user.UserDao;

import org.junit.Test;

public class DaoFactoryTest {
	@Test
	public void testGetUserDao_development() throws FileNotFoundException, SQLException, ConfigurationException, PropertyVetoException {
		System.setProperty("MYENVIRONMENT","development");
		
		Connection connection = null;
		try {
			UserDao userDao = DaoFactory.getUserDao();
			connection = userDao.getConnection();

			DatabaseMetaData dmd = connection.getMetaData();
			assertThat(dmd.getDriverName(), is("H2 JDBC Driver"));
			assertThat(dmd.getURL(), is("jdbc:h2:~/slipp-user"));
		}finally{
			if(connection != null)
				connection.close();
		}
	}

	
	@Test
	public void testGetUserDao_test() throws FileNotFoundException, SQLException, ConfigurationException, PropertyVetoException {
		System.setProperty("MYENVIRONMENT","test");
		
		Connection connection = null;
		try {
			UserDao userDao = DaoFactory.getUserDao();
			connection = userDao.getConnection();

			DatabaseMetaData dmd = connection.getMetaData();
			assertThat(dmd.getDriverName(), is("MySQL-AB JDBC Driver"));
			assertThat(dmd.getURL(), is("jdbc:mysql://localhost:3306/slipp_user_test"));
		}finally{
			if(connection != null)
				connection.close();
		}
	}

	@Test
	public void testGetUserDao_production() throws FileNotFoundException, SQLException, ConfigurationException, PropertyVetoException {
		System.setProperty("MYENVIRONMENT","production");
		
		Connection connection = null;
		try {
			UserDao userDao = DaoFactory.getUserDao();
			connection = userDao.getConnection();

			DatabaseMetaData dmd = connection.getMetaData();
			assertThat(dmd.getDriverName(), is("MySQL-AB JDBC Driver"));
			assertThat(dmd.getURL(), is("jdbc:mysql://localhost:3306/slipp_user"));
		}finally {
			if(connection != null)
				connection.close();
		}
	}
}
