package net.slipp.dao.user;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import net.slipp.domain.user.User;
import net.slipp.factory.DaoFactory;
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
	public void crudH2() throws Exception {
		User expected = new User("userId", "password", "name", "javajigi@email.com");
	
		UserDao userDao = DaoFactory.getUserDao();
		userDao.deleteAllUser();
		userDao.insert(expected);
	
		User actual = userDao.findByUserId(expected.getUserId());
		assertThat(actual, is(expected));
	}
}
