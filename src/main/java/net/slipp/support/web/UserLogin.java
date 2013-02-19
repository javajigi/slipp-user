package net.slipp.support.web;

import java.beans.PropertyVetoException;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import javax.naming.ConfigurationException;
import javax.servlet.http.HttpServletRequest;

import net.slipp.service.user.PasswordMismatchException;
import net.slipp.service.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserLogin{
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/user/Login.do")
	public ModelAndView excute(HttpServletRequest request)
			throws FileNotFoundException, ConfigurationException, SQLException, PropertyVetoException {
		ModelAndView mav = new ModelAndView("redirect:/");
		try {
			request.getSession().setAttribute(
    				"loginUser",
    				this.userService.login(
    						request.getParameter("userId"), 
    						request.getParameter("password")));
 
    	} catch (PasswordMismatchException e) {
    		mav = new ModelAndView("/user/login.jsp");
    		mav.addObject("errorMessage", e.getMessage());
    	}
		return mav;
	}

}
