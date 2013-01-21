package net.slipp.dao.user;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import net.slipp.domain.user.User;
import net.slipp.factory.DaoFactory;
import net.slipp.support.jdbc.ConnectionManager;
import net.slipp.support.jdbc.MySQLConnectionManagerImpl;

import org.junit.Before;
import org.junit.Test;

public class UserDaoTest {
	User expectedUser;
	UserDao userDao;
	
	@Before
	public void setup(){
		expectedUser = new User("userId", "password", "name", "javajigi@email.com");
	}
	
	@Test
	public void crud() throws Exception {
		 
		userDao = new TestUserDaoImpl();
		userDao.insert(expectedUser);
		
		User actual = userDao.findByUserId(expectedUser.getUserId());
		assertThat(actual, is(expectedUser));
	}
	
	@Test
	public void crudH2() throws Exception {
	
		userDao = DaoFactory.getUserDao();
		userDao.deleteAllUser();
		userDao.insert(expectedUser);
	
		User actual = userDao.findByUserId(expectedUser.getUserId());
		assertThat(actual, is(expectedUser));
	}
}
