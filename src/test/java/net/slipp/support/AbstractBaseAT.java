package net.slipp.support;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AbstractBaseAT {
	protected SharedDriver driver;

	@Before
	public void setup() {
		driver = new SharedDriver();
		driver.deleteAllCookies();
		driver.manage().window().maximize();
	}

	protected void 조작_이동(String url) {
		driver.get(url);
	}

	protected void 조작_이동(String url,String expectedTitle) {
		driver.get(url);
		검증_타이틀(expectedTitle);
	}
	
	protected void 조작_입력(String cssSelector, String text) {
		조작_입력(By.cssSelector(cssSelector),text);
	}
	
	protected void 조작_입력(By by, String text) {
		찾기_엘리먼트(by).clear();
		찾기_엘리먼트(by).sendKeys(text);
	}

	protected void 조작_클릭(String cssSelector) {
		조작_클릭(By.cssSelector(cssSelector));
	}

	protected void 조작_클릭(By by) {
		찾기_엘리먼트(by).click();
	}
	
	protected WebElement 찾기_엘리먼트(String cssSelector) {
		return 찾기_엘리먼트(By.cssSelector(cssSelector));
	}
	
	protected WebElement 찾기_엘리먼트(By by) {
		return driver.findElement(by);
	}
	
	protected void 검증_타이틀(String title) {
		assertThat(driver.getTitle(), is(title));
	}

	protected void 검증_텍스트(WebElement element,String expectedText) {
		assertThat(element.getText(), is(expectedText));
	}
	
	protected void 검증_텍스트(By by,String expectedText) {
		검증_텍스트(찾기_엘리먼트(by), expectedText);
	}
	
	protected void 검증_텍스트(String cssSelector,String expectedText) {
		검증_텍스트(By.cssSelector(cssSelector),expectedText);
	}
}
