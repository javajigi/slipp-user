package net.slipp;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HomeAT {
	private WebDriver driver;

	@Test
	public void index() {
		System.setProperty("webdriver.firefox.bin", "D:\\tools\\firefox\\firefox.exe");
		driver = new FirefoxDriver();
		driver.get("http://localhost:8080");
		assertThat(driver.getTitle(), is("SLiPP"));
	}
	
	@After
	public void teardown() {
		driver.quit();
	}
}
