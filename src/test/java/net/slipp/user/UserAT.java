package net.slipp.user;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import net.slipp.support.AbstractBaseAT;

import org.junit.Test;
import org.openqa.selenium.By;

public class UserAT extends AbstractBaseAT {
	@Test
	public void 정상적인_회원가입() {
		String userId = "javajigi";
		회원가입(userId, "password");
	}
	
	private void 회원가입(String userId, String password) {
		// given
		goIndex();
		goJoin();
		// when
		join(userId, password);
		// then
		verifyCompletedJoin(userId);
	}

	@Test
	public void 아이디가_존재하는_경우_회원가입() {
		// given
		String userId = "sanjigi";
		회원가입(userId, "password");
		goJoin();
		
		// when
		join(userId, "password");
		
		// then
		expectedAlreadyExistedErrorMessage(userId);
	}
	
	@Test
	public void 개인정보수정() throws Exception {
		String userId = "slipper";
		String password = "test1234";
		회원가입(userId, password);
		goLogin();
		login(userId, password);
		assertThat(찾기_엘리먼트(By.linkText("로그아웃")), is(notNullValue()));
		
		조작_클릭(By.linkText("개인정보수정"));
		
		조작_입력("#password", password);
		조작_입력("#name", "자바지기");
		조작_입력("#email", "javajigi@slipp.net");
		
		조작_클릭("button.btn.btn-primary");
	}
	
	@Test
	public void 로그인_성공() throws Exception {
		goIndex();
		goLogin();
		login("admin", "password");
		
		assertThat(찾기_엘리먼트(By.linkText("로그아웃")), is(notNullValue()));
	}

	private void goLogin() {
		조작_클릭(By.linkText("로그인"));
		검증_타이틀("SLiPP :: 로그인");
	}

	@Test
	public void 로그인_실패() throws Exception {
		goIndex();
		goLogin();
		login("admin", "password2");
		
		assertThat(찾기_엘리먼트(By.linkText("로그인")), is(notNullValue()));
		검증_텍스트("div.error", "아이디와 비밀번호가 틀립니다.");
	}

	@Test
	public void 로그아웃() throws Exception {
		goIndex();
		goLogin();
		login("admin", "password");
		
		조작_클릭(By.linkText("로그아웃"));
		assertThat(찾기_엘리먼트(By.linkText("로그인")), is(notNullValue()));
	}
	
	public void login(String userId, String password) {
		조작_입력("#userId", userId);
		조작_입력("#password", password);
		조작_클릭("button.btn.btn-primary");
	}
	
	private void expectedAlreadyExistedErrorMessage(String userId) {
		검증_텍스트("div.messageForm",userId + "는 이미 존재하는 아이디입니다.");
	}

	public void verifyCompletedJoin(String userId) {
		검증_텍스트("div.messageForm > p",userId + " 계정으로 회원가입 완료되었습니다.");
	}

	public void join(String userId, String password) {
		조작_입력("#userId", userId);
		조작_입력("#password", password);
		조작_입력("#name", "박재성");
		조작_입력("#email", "javajigi@gmail.com");
		조작_클릭("button.btn.btn-primary");
	}

	public void goJoin() {
		조작_클릭(By.linkText("회원가입"));
		검증_타이틀("SLiPP :: 회원가입");
	}

	public void goIndex() {
		조작_이동("http://localhost:8080/","SLiPP");
	}
}
