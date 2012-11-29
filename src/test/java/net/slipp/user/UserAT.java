package net.slipp.user;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class UserAT {
	private WebDriver driver;

	@Before
	public void setup() {
		// System.setProperty("webdriver.firefox.bin", "D:\\tools\\firefox\\firefox.exe");
		driver = new FirefoxDriver();
	}

	@Test
	public void 정상적인_회원가입() {
		goIndex();
		goJoin();
		String userId ="javajigi";
		join(userId);
		verifyCompletedJoin(userId);
	}
	
	@Test
	public void 아이디가_존재하는_경우_회원가입() {
		goIndex();
		goJoin();
		String userId ="sanjigi";
		join(userId);
		verifyCompletedJoin(userId);
		
		goJoin();
		join(userId);
		expectedAlreadyExistedErrorMessage(userId);
	}

	private void expectedAlreadyExistedErrorMessage(String userId) {
		String actualMessage = driver.findElement(By.cssSelector("div.error")).getText();
		assertThat(actualMessage, is(userId + "는 이미 존재하는 아이디입니다."));
	}

	public void verifyCompletedJoin(String userId) {
		String actualMessage = driver.findElement(By.cssSelector("div.messageForm > p")).getText();
		assertThat(actualMessage, is(userId + " 계정으로 회원가입 완료되었습니다."));
	}

	public void join(String userId) {
		driver.findElement(By.id("userId")).clear();
		driver.findElement(By.id("userId")).sendKeys(userId);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("test1234");
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("박재성");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("javajigi@gmail.com");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
	}

	public void goJoin() {
		driver.findElement(By.linkText("회원가입")).click();
		assertThat(driver.getTitle(), is("SLiPP :: 회원가입"));
	}

	public void goIndex() {
		driver.get("http://localhost:8080");
		assertThat(driver.getTitle(), is("SLiPP"));
	}

	@After
	public void teardown() {
		driver.quit();
	}
}
