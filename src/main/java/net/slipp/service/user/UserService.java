package net.slipp.service.user;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

import net.slipp.dao.user.UserDao;
import net.slipp.domain.user.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {
	private static Logger log = LoggerFactory.getLogger(UserService.class);
	private UserDao userDao = null;
	
	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public User join(User user) throws SQLException, ExistedUserException, PropertyVetoException {
		log.debug("User : {}", user);
		User existedUser = userDao.findByUserId(user.getUserId());
		if (existedUser != null) {
			throw new ExistedUserException(user.getUserId());
		}

		userDao.insert(user);
		return user;
	}
	
	public User update(User user) throws SQLException, PropertyVetoException {
	    User existedUser = userDao.findByUserId(user.getUserId());
	    userDao.update(user);
	    
	    return existedUser;
	}

	public User login(String userId, String password) throws SQLException, PasswordMismatchException, PropertyVetoException {
		User user = userDao.findByUserId(userId);
		if (user == null) {
			throw new PasswordMismatchException();
		}
		
		if (!user.matchPassword(password)) {
			throw new PasswordMismatchException();
		}

		return user;
	}

	public User findByUserId(String userId) throws SQLException, PropertyVetoException {
		return userDao.findByUserId(userId);
	}

	public void deleteAllUser() throws SQLException, PropertyVetoException {
		userDao.deleteAllUser();
	}
}
