<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="net.slipp.user.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SLiPP :: 회원가입</title>

<%@ include file="../commons/_header.jspf"%>

</head>

<%
	User user = new User();
	String userId = request.getParameter("userId");
	user.setUserId(userId);
	user.setPassword(request.getParameter("password"));
	user.setName(request.getParameter("name"));
	user.setEmail(request.getParameter("email"));
	UserService userService = new UserService();
	userService.create(user);
%>

<body>
	<%@ include file="../commons/_top.jspf"%>

	<div class="container">
		<div class="row">
			<div class="span12">
				<section id="typography">
				<div class="page-header">
					<h1>회원가입</h1>
				</div>
				<div class="messageForm">
					<p><%= userId %> 계정으로 회원가입 완료되었습니다.</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>