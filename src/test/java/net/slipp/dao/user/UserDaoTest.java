package net.slipp.dao.user;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import net.slipp.domain.user.User;

import org.junit.Test;

public class UserDaoTest {
	@Test
	public void crud() throws Exception {
		User expected = new User();
		expected.setUserId("userId");
		expected.setPassword("password");
		expected.setName("이름");
		expected.setEmail("javajigi@gmail.com");
		UserDao userDao = new UserDao();
		userDao.save(expected);
		
		User actual = userDao.findByUserId(expected.getUserId());
		assertThat(actual, is(expected));
	}
}
