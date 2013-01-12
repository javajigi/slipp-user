package net.slipp.factory;

import java.io.FileNotFoundException;

import javax.naming.ConfigurationException;

import net.slipp.dao.user.UserDao;
import net.slipp.service.user.UserService;

public class ServiceFactory {
	public static UserService getUserService() throws FileNotFoundException, ConfigurationException { 
		UserDao userDao = DaoFactory.getUserDao();
		return new UserService(userDao);
	}
}
