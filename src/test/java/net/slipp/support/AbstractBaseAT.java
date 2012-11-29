package net.slipp.support;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AbstractBaseAT {
	protected WebDriver driver;

	@Before
	public void setup() {
		System.setProperty("webdriver.firefox.bin", "D:\\tools\\firefox\\firefox.exe");
		driver = new FirefoxDriver();
	}
	
	@After
	public void teardown() {
		driver.quit();
	}
}
