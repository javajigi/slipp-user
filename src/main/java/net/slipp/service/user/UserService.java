package net.slipp.service.user;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

import net.slipp.dao.user.UserDao;
import net.slipp.domain.user.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	private static Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;
	
	public User join(User user) throws ExistedUserException {
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
