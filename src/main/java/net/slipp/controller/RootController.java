package net.slipp.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.slipp.domain.user.User;
import net.slipp.service.user.PasswordMismatchException;
import net.slipp.service.user.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RootController {

	@Resource
	private UserService userService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String goRoot() {
		return "/index";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String excuteLogin(HttpServletRequest request, @ModelAttribute User user, Model model) {
		try {
			user = userService.login(user.getUserId(), user.getPassword());
			request.getSession().setAttribute("loginUser", user);
    	} catch (PasswordMismatchException e) {
    		model.addAttribute("errorMessage", e.getMessage());
    		return "/user/login";
    	}
		return "redirect:/";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String excuteGetLoginForm(HttpServletRequest request){
		return "/user/login";
	}
	
	@RequestMapping(value="/logout_action", method=RequestMethod.GET)
	public String logout(HttpServletRequest request){
		request.getSession().invalidate();
		return "redirect:/";
	}
}
