package net.slipp.service.user;

import java.sql.SQLException;

import net.slipp.dao.user.UserDao;
import net.slipp.domain.user.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {
	private static Logger log = LoggerFactory.getLogger(UserService.class);
	
	public User join(User user) throws SQLException {
		log.debug("User : {}", user);
		UserDao userDao = new UserDao();
		userDao.insert(user);
		return user;
	}

}
