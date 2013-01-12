package net.slipp.dao.user;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import net.slipp.domain.user.User;
import net.slipp.factory.DaoFactory;

import org.junit.Test;

public class UserDaoTest {
	@Test
	public void crud() throws Exception {
		User expected = new User("userId", "password", "name", "javajigi@email.com");
		 
		//UserDao userDao = DaoFactory.getUserDao();
		UserDao userDao = new TestUserDaoImpl();
		userDao.insert(expected);
		
		User actual = userDao.findByUserId(expected.getUserId());
		assertThat(actual, is(expected));
	}
}
