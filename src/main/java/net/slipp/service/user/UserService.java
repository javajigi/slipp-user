package net.slipp.service.user;

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
	
	public User update(User user) throws NotFoundExistedUserException {
	    User existedUser = userDao.findByUserId(user.getUserId());
	    if(existedUser == null) {
	    	throw new NotFoundExistedUserException(user.getUserId());
	    }
	    userDao.update(user);
	    return user;
	}

	public User login(String userId, String password) throws PasswordMismatchException {
		User user = userDao.findByUserId(userId);
		if (user == null) {
			throw new PasswordMismatchException();
		}
		
		if (!user.matchPassword(password)) {
			throw new PasswordMismatchException();
		}

		return user;
	}

	public User findByUserId(String userId) {
		return userDao.findByUserId(userId);
	}

	public void deleteAllUser() {
		userDao.deleteAllUser();
	}
}
