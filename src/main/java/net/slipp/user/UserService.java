package net.slipp.user;

import java.sql.SQLException;

public class UserService {
	public void create(User user) throws SQLException {
		UserDao userDao = new UserDao();
		userDao.save(user);
	}
}
