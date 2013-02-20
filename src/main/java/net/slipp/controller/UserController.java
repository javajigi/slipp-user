package net.slipp.controller;

import java.beans.PropertyVetoException;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import javax.naming.ConfigurationException;
import javax.servlet.http.HttpServletRequest;

import net.slipp.domain.user.User;
import net.slipp.service.user.ExistedUserException;
import net.slipp.service.user.PasswordMismatchException;
import net.slipp.service.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/user/Join.do")
	public ModelAndView excuteJoin(HttpServletRequest request, @ModelAttribute User user) {
		ModelAndView mav = new ModelAndView("/user/join_action");
		try {
			String userId = userService.join(user).getUserId();
			mav.addObject("result", userId + " 계정으로 회원가입 완료되었습니다.");
		} catch(ExistedUserException e){
			mav.addObject("result", e.getMessage());
		}
		return mav;
	}
	
	@RequestMapping("/user/JoinForm.do")
	public String excuteGetJoinForm(HttpServletRequest request) {
		return "/user/join";
	}
	
	@RequestMapping("/user/Login.do")
	public ModelAndView excuteLogin(HttpServletRequest request)
			throws FileNotFoundException, ConfigurationException, SQLException, PropertyVetoException {
		ModelAndView mav = new ModelAndView("/index");
		try {
			User user = userService.login(request.getParameter("userId"), request.getParameter("password"));
			request.getSession().setAttribute("loginUser", user);
    	} catch (PasswordMismatchException e) {
    		e.printStackTrace();
    		mav = new ModelAndView("/user/login");
    		mav.addObject("errorMessage", e.getMessage());
    	}
		return mav;
	}
	
	@RequestMapping("/user/LoginForm.do")
	public String excuteGetLoginForm(HttpServletRequest request){
		return "/user/login";
	}
	
	@RequestMapping("/user/update.do")
	public ModelAndView executeUpdate(HttpServletRequest request, @ModelAttribute User user) throws SQLException, PropertyVetoException{
		ModelAndView mav = new ModelAndView("redirect:/user/update.jsp");
		user = userService.update(user);
		request.getSession().setAttribute("loginUser", user);
		return mav;
	}
}
