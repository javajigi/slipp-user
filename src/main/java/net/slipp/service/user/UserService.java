package net.slipp.service.user;

import java.sql.SQLException;

import net.slipp.dao.user.UserDao;
import net.slipp.domain.user.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {
	private Logger log = LoggerFactory.getLogger(UserService.class);
	
	private UserDao userDao;

	public UserService() {}
	
	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}

	public void create(User user) throws SQLException, ExistedUserException {
		log.debug("user : {}", user);
		
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
