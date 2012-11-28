package net.slipp.dao.user;

import net.slipp.domain.user.User;

import org.junit.Test;

public class UserDaoTest {

	@Test
	public void insert() throws Exception {
		User user = new User("userId", "password", "name", "javajigi@email.com");
		UserDao userDao = new UserDao();
		userDao.insert(user);
	}

}
