package net.slipp.support.web;

import java.beans.PropertyVetoException;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import javax.naming.ConfigurationException;
import javax.servlet.http.HttpServletRequest;
import net.slipp.domain.user.User;
import net.slipp.factory.ServiceFactory;
import net.slipp.service.user.ExistedUserException;

public class UserJoinHandler implements Handler {
	@Override
	public String excute(HttpServletRequest request) 
			throws FileNotFoundException, ConfigurationException, SQLException, 
			PropertyVetoException {
		
		User user = new User(
				request.getParameter("userId"), 
				request.getParameter("password"),
				request.getParameter("name"),
				request.getParameter("email"));
		
		try {
			String userId = ServiceFactory.getUserService().join(user).getUserId();
			request.setAttribute("result", userId + " 계정으로 회원가입 완료되었습니다.");
		} catch(ExistedUserException e){
			request.setAttribute("result", e.getMessage());
		}
		
		return "/user/join_action.jsp";
	}
}
