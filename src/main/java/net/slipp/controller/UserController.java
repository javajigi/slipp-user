package net.slipp.controller;

import javax.servlet.http.HttpServletRequest;

import net.slipp.domain.user.User;
import net.slipp.exception.ExistedUserException;
import net.slipp.exception.NotFoundExistedUserException;
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

	private static final String USER_JOIN_ACTION = "/user/join_action";

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String excuteJoin(HttpServletRequest request,
			@ModelAttribute User user, Model model) {
		try {
			String userId = userService.join(user).getUserId();
			model.addAttribute("result", userId + " 계정으로 회원가입 완료되었습니다.");
		} catch (ExistedUserException e) {
			model.addAttribute("result", e.getMessage());
		}
		return USER_JOIN_ACTION;
	}

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String excuteGetJoinForm(HttpServletRequest request) {
		return "/user/join";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String executeUpdate(HttpServletRequest request,
			@ModelAttribute User user, Model model) {
		try {
			user = userService.update(user);
		} catch (NotFoundExistedUserException e) {
			return "redirect:/login";
		}
		request.getSession().setAttribute("loginUser", user);
		return "redirect:/";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String executeGetUpdateForm() {
		return "/user/update";
	}
}
