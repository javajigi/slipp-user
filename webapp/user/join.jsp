<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SLiPP :: 회원가입</title>
<link href="/stylesheets/bootstrap.css" rel="stylesheet">
<link href="/stylesheets/bootstrap-responsive.css" rel="stylesheet">
<link href="/stylesheets/docs.css" rel="stylesheet">
<style>
body {
	padding-top: 43px;
}
</style>
</head>
<body>
    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a href="/">지속가능한 삶, 프로그래밍, 프로그래머</a>
          <div class="nav-collapse collapse pull-right">
            <ul class="nav">
              <li class="loginBtn"><a href="/user/join.jsp">회원가입</a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
	<div class="container">
		<div class="row">
			<div class="span10">
				<form class="form-horizontal">
					<div class="control-group">
						<label class="control-label" for="inputEmail">사용자 아이디</label>
						<div class="controls">
							<input type="text" id="inputUserId" placeholder="">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputPassword">Password</label>
						<div class="controls">
							<input type="password" id="inputPassword" placeholder="Password">
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<button type="submit" class="btn">회원가입</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>