<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
					<h1>글쓰기</h1>
				</div>
				<form class="form-horizontal" action="/board/board_action.jsp" method="post">
					<div class="control-group">
						<label class="control-label" for="userId">제목</label>
						<div class="controls">
							<input type="text" id="userId" name="userId" class="span8" placeholder="">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="password">내용</label>
						<div class="controls">
							<textarea rows="5" class="span8"></textarea>
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<button type="submit" class="btn btn-primary">글쓰기</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>