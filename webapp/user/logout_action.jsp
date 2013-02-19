<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	System.out.println("logout action called...................");
	session.removeAttribute("loginUser");
	response.sendRedirect("/");
%>
