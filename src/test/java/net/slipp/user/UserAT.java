package net.slipp.user;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import net.slipp.support.AbstractBaseAT;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
		assertThat(driver.findElement(By.linkText("로그아웃")), is(notNullValue()));
		
		driver.findElement(By.linkText("개인정보수정")).click();
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("자바지기");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("javajigi@slipp.net");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
	}
	
	@Test
	public void 로그인_성공() throws Exception {
		goIndex();
		goLogin();
		login("admin", "password");
		
		assertThat(driver.findElement(By.linkText("로그아웃")), is(notNullValue()));
	}

	private void goLogin() {
		driver.findElement(By.linkText("로그인")).click();
		assertThat(driver.getTitle(), is("SLiPP :: 로그인"));
	}
	
	@Test
	public void 로그인_실패() throws Exception {
		goIndex();
		goLogin();
		login("admin", "password2");
		
		assertThat(driver.findElement(By.linkText("로그인")), is(notNullValue()));
		String actualMessage = driver.findElement(By.cssSelector("div.error")).getText();
		assertThat(actualMessage, is("아이디와 비밀번호가 틀립니다."));
	}

	@Test
	public void 로그아웃() throws Exception {
		goIndex();
		goLogin();
		login("admin", "password");
		
		driver.findElement(By.linkText("로그아웃")).click();
		assertThat(driver.findElement(By.linkText("로그인")), is(notNullValue()));
	}
	
	public void login(String userId, String password) {
		driver.findElement(By.id("userId")).clear();
		driver.findElement(By.id("userId")).sendKeys(userId);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
	}
	
	private void expectedAlreadyExistedErrorMessage(String userId) {
		String actualMessage = driver.findElement(By.cssSelector("div.error")).getText();
		assertThat(actualMessage, is(userId + "는 이미 존재하는 아이디입니다."));
	}

	public void verifyCompletedJoin(String userId) {
		String actualMessage = driver.findElement(By.cssSelector("div.messageForm > p")).getText();
		assertThat(actualMessage, is(userId + " 계정으로 회원가입 완료되었습니다."));
	}

	public void join(String userId, String password) {
		driver.findElement(By.id("userId")).clear();
		driver.findElement(By.id("userId")).sendKeys(userId);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("박재성");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("javajigi@gmail.com");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
	}

	public void goJoin() {
		WebElement element = driver.findElement(By.linkText("회원가입"));
		element.click();
		assertThat(driver.getTitle(), is("SLiPP :: 회원가입"));
	}

	public void goIndex() {
		driver.get("http://localhost:8080/");
		assertThat(driver.getTitle(), is("SLiPP"));
	}
}
