<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="net.slipp.domain.user.*" %>
<%@ page import="net.slipp.service.user.*" %>

<%
	String userId = request.getParameter("userId");
	String password = request.getParameter("password");
	UserService userService = new UserService();
	User user = userService.login(userId, password);
	
	session.setAttribute("loginUser", user);
	
	response.sendRedirect("/");
%>
