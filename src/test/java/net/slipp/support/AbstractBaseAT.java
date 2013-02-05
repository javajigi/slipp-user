package net.slipp.support;

import org.junit.Before;

public class AbstractBaseAT {
	protected SharedDriver driver;

	@Before
	public void setup() {
		driver = new SharedDriver();
		driver.deleteAllCookies();
		driver.manage().window().maximize();
	}
}
