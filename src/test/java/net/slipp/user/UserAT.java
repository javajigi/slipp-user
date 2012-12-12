package net.slipp.user;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
		driver = new FirefoxDriver();
	}

	@Test
	public void 회원가입() throws Exception {
		
		driver.get("http://localhost:8080");
		assertThat(driver.getTitle(), is("SLiPP"));
		
		driver.findElement(By.linkText("회원가입")).click();
		assertThat(driver.getTitle(), is("SLiPP :: 회원가입"));
	}
	
	@After
	public void teardown() {
		driver.close();
	}
}
