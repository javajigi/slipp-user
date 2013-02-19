package net.slipp.support.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserJoinForm {
	
	@RequestMapping("/user/JoinForm.do")
	public String excute(HttpServletRequest request) {
		return "/user/join.jsp";
	}

}
