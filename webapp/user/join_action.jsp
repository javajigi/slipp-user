<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="net.slipp.domain.user.*" %>
<%@ page import="net.slipp.service.user.*" %>
<%@ page import="net.slipp.factory.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SLiPP :: 회원가입</title>

<%@ include file="../commons/_header.jspf"%>

</head>
<body>
	<%@ include file="../commons/_top.jspf"%>

	<div class="container">
		<div class="row">
			<div class="span12">
				<section id="typography">
				<div class="page-header">
					<h1>회원가입</h1>
				</div>
				<div class="${error?'error':'messageForm'}">
					<p>${result}</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>