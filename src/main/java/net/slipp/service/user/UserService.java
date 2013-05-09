package net.slipp.service.user;

import java.sql.SQLException;

import net.slipp.dao.user.UserDao;
import net.slipp.domain.user.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {
	private static Logger log = LoggerFactory.getLogger(UserService.class);

	public User join(User user) throws SQLException, ExistedUserException {
		log.debug("User : {}", user);
		UserDao userDao = new UserDao();
		User existedUser = userDao.findByUserId(user.getUserId());
		if (existedUser != null) {
			throw new ExistedUserException(user.getUserId());
		}

		userDao.insert(user);
		return user;
	}
	
	public User update(User user) throws SQLException {
		UserDao userDao = new UserDao();
		userDao.update(user);
		return user;
	}

	public User login(String userId, String password) throws SQLException, PasswordMismatchException {
		UserDao userDao = new UserDao();
		User user = userDao.findByUserId(userId);
		if (user == null) {
			throw new PasswordMismatchException();
		}
		
		if (!user.matchPassword(password)) {
			throw new PasswordMismatchException();
		}

		return user;
	}

	public User findByUserId(String userId) throws SQLException {
		UserDao userDao = new UserDao();
		return userDao.findByUserId(userId);
	}
}
