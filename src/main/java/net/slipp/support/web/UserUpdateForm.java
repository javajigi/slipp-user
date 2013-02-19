package net.slipp.support.web;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.slipp.domain.user.User;
import net.slipp.service.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserUpdateForm {

	@Autowired
	private UserService userService;
	
	public ModelAndView execute(HttpServletRequest request) throws SQLException, PropertyVetoException{
		ModelAndView mav = new ModelAndView("/user/update.jsp");
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
		User user = userService.findByUserId(loginUser.getUserId());
		if(user == null){
			return new ModelAndView("redirect:/login.do");
		}
		mav.addObject("loginUser", user);
		return mav;
	}
}
