package net.slipp;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HomeAT {
	@Test
	public void index() throws Exception {
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.slipp.net");
		assertThat(driver.getTitle(), is("SLiPP"));
		driver.close();
	}
}
