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
					<h1>수정하기</h1>
				</div>
				<form class="form-horizontal" action="/board/board_action.jsp" method="post">
					<div class="control-group">
						<label class="control-label" for="userId">제목</label>
						<div class="controls">
							<input type="text" id="userId" name="userId" class="span8" placeholder="" value="소프트웨어 공학 여섯번째 수업 - 개발 환경">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="password">내용</label>
						<div class="controls">
							<textarea rows="5" class="span8">
지난 주까지 서비스에 대한 기능 목록 추출, 우선순위 선정, 일정 추정 작업을 완료했다. 드디어 개발을 시작한다는 기대감 때문인지 지난 주부터 슬슬 적극성을 보이기 시작했다. 이번 주는 기능 중 하나를 선택해 기능을 구현해 보기로 했다.

스프린트 0에서는 개발 서버 설정, 개발자 PC 개발 환경 세팅, 샘플 소스 코드 공유 및 학습 등의 과정을 거친다. 이 목적을 위해 지난 시간에 기능 중 하나를 선택했다. 우리가 선택한 기능은 구현 복잡도가 그리 높지 않으며, 우선순위가 높고, frontend부터 backend까지를 관통하는 기능을 선택했다. 그래야 개발 초반에 고려할 수 있는 부분을 최대한 고려할 수 있다고 생각하기 때문이다.
먼저 구현할 기능에 대한 간략한 모델을 설계했다. 모델명을 정하고 모델이 가지는 속성을 정하는 시간을 가졌다.
							</textarea>
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<button type="submit" class="btn btn-primary">수정</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>