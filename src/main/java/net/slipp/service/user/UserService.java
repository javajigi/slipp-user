package net.slipp.service.user;

import java.sql.SQLException;

import net.slipp.dao.user.UserDao;
import net.slipp.domain.user.User;

public class UserService {
	public void create(User user) throws SQLException {
		UserDao userDao = new UserDao();
		userDao.save(user);
	}
}
