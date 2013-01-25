<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.*" %>
<%@ page import="net.slipp.domain.user.*" %>
<%@ page import="net.slipp.service.user.*" %>
<%@ page import="net.slipp.factory.*" %>

<%
	String userId = request.getParameter("userId");
	String password = request.getParameter("password");
	UserService userService = ServiceFactory.getUserService();
	
	try {
		User user = userService.login(userId, password);
		session.setAttribute("loginUser", user);
		response.sendRedirect("/");
	} catch (PasswordMismatchException e) {
		request.setAttribute("errorMessage", e.getMessage());
		RequestDispatcher rd = request.getRequestDispatcher("/user/login.jsp");
		rd.forward(request, response);	
	}
%>
