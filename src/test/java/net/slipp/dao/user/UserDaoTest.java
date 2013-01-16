package net.slipp.dao.user;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import net.slipp.domain.user.User;
import net.slipp.support.jdbc.ConnectionManager;
import net.slipp.support.jdbc.MySQLConnectionManagerImpl;

import org.junit.Test;

public class UserDaoTest {
	@Test
	public void crud() throws Exception {
		User expected = new User("userId", "password", "name", "javajigi@email.com");
		 
		UserDao userDao = new TestUserDaoImpl();
		userDao.insert(expected);
		
		User actual = userDao.findByUserId(expected.getUserId());
		assertThat(actual, is(expected));
	}
	@Test
	public void crudMySQL() throws Exception {
		User expected = new User("userId", "password", "name", "javajigi@email.com");
	
		Map<String, Object> config = new HashMap(); 
		config.put("adapter", "mysql");
		config.put("connectionpool", "dhcp");
		config.put("protocol", "jdbc");
		config.put("host", "localhost");
		config.put("port", "3306");
		config.put("database", "slipp_user_test");
		config.put("username", "root");
		config.put("password", "16241624");
	
		ConnectionManager connectionManager = new MySQLConnectionManagerImpl(config);
		UserDao userDao = new UserDaoImpl(connectionManager);
		userDao.deleteAllUser();
		userDao.insert(expected);
	
		User actual = userDao.findByUserId(expected.getUserId());
		assertThat(actual, is(expected));
	}
}
