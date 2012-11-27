package net.slipp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HomeAT {
	private WebDriver driver;

	@Before
	public void setup() {
		// System.setProperty("webdriver.firefox.bin", "D:\\tools\\firefox\\firefox.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}
	
	@Test
	public void index() throws Exception {
		driver.get("http://localhost:8080");
		assertThat(driver.getTitle(), is("SLiPP"));
	}
	
	@After
	public void teardown() {
		driver.quit();
	}
}
