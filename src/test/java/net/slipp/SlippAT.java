package net.slipp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SlippAT {
	private WebDriver driver;

	@Before
	public void setup() {
		System.setProperty("webdriver.firefox.bin", "D:\\tools\\firefox\\firefox.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

	@Test
	public void 회원가입() throws Exception {
		driver.get("http://localhost:8080");
		assertThat(driver.getTitle(), is("SLiPP"));
		driver.findElement(By.linkText("회원가입")).click();
		assertThat(driver.getTitle(), is("SLiPP :: 회원가입"));
		
		String userId = "javajigi";
		driver.findElement(By.id("userId")).clear();
		driver.findElement(By.id("userId")).sendKeys(userId);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("test1234");
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("박재성");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("javajigi@gmail.com");
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();

		String actualMessage = driver.findElement(By.cssSelector("div.messageForm > p")).getText();
		assertThat(actualMessage, is(userId + " 계정으로 회원가입 완료되었습니다."));
	}

	@After
	public void teardown() {
		// driver.quit();
	}
}