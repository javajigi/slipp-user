package net.slipp.service.user;

import net.slipp.domain.user.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {
	private static Logger log = LoggerFactory.getLogger(UserService.class);
	
	public User join(User user) {
		log.debug("User : {}", user);
		return user;
	}

}
