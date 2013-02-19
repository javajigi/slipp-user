package net.slipp.support.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserLoginForm {
	@RequestMapping("/user/LoginForm.do")
	public ModelAndView excute(HttpServletRequest request){
		return new ModelAndView("/user/login.jsp");
	}
}
