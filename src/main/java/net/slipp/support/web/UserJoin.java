package net.slipp.support.web;

import java.beans.PropertyVetoException;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import javax.naming.ConfigurationException;
import javax.servlet.http.HttpServletRequest;

import net.slipp.domain.user.User;
import net.slipp.service.user.ExistedUserException;
import net.slipp.service.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserJoin {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/user/Join.do")
	public ModelAndView excute(HttpServletRequest request) 
			throws FileNotFoundException, ConfigurationException, SQLException, 
			PropertyVetoException {
		ModelAndView mav = new ModelAndView("/user/join_action.jsp");
		User user = new User(
				request.getParameter("userId"), 
				request.getParameter("password"),
				request.getParameter("name"),
				request.getParameter("email"));
		try {
			String userId = userService.join(user).getUserId();
			mav.addObject("result", userId + " 계정으로 회원가입 완료되었습니다.");
		} catch(ExistedUserException e){
			mav.addObject("result", e.getMessage());
		}
		return mav;
	}
}
