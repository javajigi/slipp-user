package net.slipp.support.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.slipp.domain.user.User;
import net.slipp.factory.ServiceFactory;
import net.slipp.service.user.ExistedUserException;

@SuppressWarnings("serial")
public class UserJoinServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		User user = new User(
				req.getParameter("userId"), 
				req.getParameter("password"),
				req.getParameter("name"),
				req.getParameter("email"));
		
		try {
//		Question. 이럴 땐, 한 줄로? 세 줄로? 코딩... 어떤게 더 보기 편한 것일까?
//			UserService userService = ServiceFactory.getUserService();
//			User user = userService.join(user);
//			String userId = user.getUserId();
	
			String userId = ServiceFactory.getUserService().join(user).getUserId();
			req.setAttribute("result", userId + " 계정으로 회원가입 완료되었습니다.");
		} catch(ExistedUserException e){
			req.setAttribute("result", e.getMessage());
		} catch(Exception e) {
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		req.getRequestDispatcher("/user/join_action.jsp").forward(req, resp);   
	}
}
