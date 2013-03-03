package net.slipp.service.user;

import net.slipp.domain.user.User;
import net.slipp.exception.ExistedUserException;
import net.slipp.exception.NotFoundExistedUserException;
import net.slipp.exception.PasswordMismatchException;
import net.slipp.repository.user.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	private static Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	public User join(User user) throws ExistedUserException {
		log.debug("User : {}", user);
		User existedUser = userRepository.findByUserId(user.getUserId());
		if (existedUser != null) {
			throw new ExistedUserException(user.getUserId());
		}
		
		userRepository.save(user);
		return user;
	}
	
	public User update(User user) throws NotFoundExistedUserException {
	    User existedUser = userRepository.findByUserId(user.getUserId());
	    if(existedUser == null) {
	    	throw new NotFoundExistedUserException(user.getUserId());
	    }
	    userRepository.save(user);
	    return user;
	}

	public User login(String userId, String password) throws PasswordMismatchException {
		User user = userRepository.findByUserId(userId);
		if (user == null) {
			throw new PasswordMismatchException();
		}
		
		if (!user.matchPassword(password)) {
			throw new PasswordMismatchException();
		}

		return user;
	}

	public User findByUserId(String userId) {
		return userRepository.findByUserId(userId);
	}

	public void deleteAllUser() {
		userRepository.deleteAll();
	}
}
