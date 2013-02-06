package net.slipp.support.web;

import java.beans.PropertyVetoException;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import javax.naming.ConfigurationException;
import javax.servlet.http.HttpServletRequest;
import net.slipp.factory.ServiceFactory;
import net.slipp.service.user.PasswordMismatchException;

public class UserLoginHandler implements Handler {

	@Override
	public String excute(HttpServletRequest request)
			throws FileNotFoundException, ConfigurationException, SQLException,
			PropertyVetoException {
		try {
			request.getSession().setAttribute(
    				"loginUser",
    				ServiceFactory.getUserService().login(
    						request.getParameter("userId"), 
    						request.getParameter("password")));
 
    	} catch (PasswordMismatchException e) {
    		request.setAttribute("errorMessage", e.getMessage());
    		return "/user/login.jsp";
    	}
 
		return "/";
	}

}
