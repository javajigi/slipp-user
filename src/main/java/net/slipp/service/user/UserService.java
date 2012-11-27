package net.slipp.service.user;

import java.sql.SQLException;

import net.slipp.dao.user.UserDao;
import net.slipp.domain.user.User;

public class UserService {
	private UserDao userDao;

	public UserService() {}
	
	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}

	public void create(User user) throws SQLException, ExistedUserException {
		if (userDao == null) {
			userDao = new UserDao();
		}
		
		User existedUser = userDao.findByUserId(user.getUserId());
		if (existedUser != null) {
			throw new ExistedUserException(user.getUserId());
		}
		
		userDao.save(user);
	}
}
