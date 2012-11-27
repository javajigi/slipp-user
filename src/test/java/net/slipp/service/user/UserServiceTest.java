package net.slipp.service.user;

import java.sql.SQLException;

import net.slipp.dao.user.UserDao;
import net.slipp.domain.user.User;

import org.junit.Test;

public class UserServiceTest {
	@Test
	public void create_정상() throws Exception {
		UserDao userDao = new UserDao() {
			@Override
			public void save(User user) throws SQLException {
			}

			@Override
			public User findByUserId(String userId) {
				return null;
			}
		};
		UserService userService = new UserService(userDao);
		userService.create(new User());
	}

	@Test(expected=ExistedUserException.class)
	public void create_이미_존재하는_사용자() throws Exception {
		final User user = new User();
		UserDao userDao = new UserDao() {
			@Override
			public void save(User user) throws SQLException {
			}
			
			@Override
			public User findByUserId(String userId) {
				return user;
			}
		};
		UserService userService = new UserService(userDao);
		userService.create(user);
	}
}
