package net.slipp.controller;

import javax.servlet.http.HttpServletRequest;

import net.slipp.domain.user.User;
import net.slipp.service.user.ExistedUserException;
import net.slipp.service.user.NotFoundExistedUserException;
import net.slipp.service.user.PasswordMismatchException;
import net.slipp.service.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/join",method=RequestMethod.POST)
	public String excuteJoin(HttpServletRequest request, @ModelAttribute User user, Model model) {
		try {
			String userId = userService.join(user).getUserId();
			model.addAttribute("result", userId + " 계정으로 회원가입 완료되었습니다.");
		} catch(ExistedUserException e){
			model.addAttribute("result", e.getMessage());
		}
		return "/user/join_action";
	}
	
	@RequestMapping(value="/join",method=RequestMethod.GET)
	public String excuteGetJoinForm(HttpServletRequest request) {
		return "/user/join";
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String excuteLogin(HttpServletRequest request, @ModelAttribute User user, Model model) {
		try {
			user = userService.login(user.getUserId(), user.getPassword());
			request.getSession().setAttribute("loginUser", user);
    	} catch (PasswordMismatchException e) {
    		model.addAttribute("errorMessage", e.getMessage());
    		return "/user/login";
    	}
		return "/index";
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String excuteGetLoginForm(HttpServletRequest request){
		return "/user/login";
	}
	
	@RequestMapping(value="/update.do", method=RequestMethod.POST)
	public String executeUpdate(HttpServletRequest request, @ModelAttribute User user, Model model) {
		try {
			user = userService.update(user);
		} catch (NotFoundExistedUserException e) {
			return "redirect:/user/login.do";
		}
		request.getSession().setAttribute("loginUser", user);
		return "/user/update";
	}
	@RequestMapping(value="/update.do", method=RequestMethod.GET)
	public String executeGetUpdateForm() {
		return "/user/update";
	}
}
