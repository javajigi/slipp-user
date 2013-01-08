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
	public void testGetUserDao() throws FileNotFoundException, SQLException, ConfigurationException, PropertyVetoException {
		UserDao userDao = DaoFactory.getUserDao();
		Connection connection = userDao.getConnection();
		
		DatabaseMetaData dmd = connection.getMetaData();
		assertThat(dmd.getDriverName(), is("H2 JDBC Driver"));
	}
}
