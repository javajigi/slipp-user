package net.slipp.support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class WebDriverFactory {
	
	private static String OS = System.getProperty("os.name").toLowerCase();
	
	private static final WebDriverType DEFAULT_BROWSER_TYPE = WebDriverType.CHROME;

	public static WebDriver createWebDriver() {
    	WebDriver driver = null;
    	
    	if (DEFAULT_BROWSER_TYPE == WebDriverType.CHROME) {
    		System.setProperty("webdriver.chrome.driver", WebDriverFactory.class.getResource("/webdriver/"+getCromeWEbDriverFileNameForOS()).getPath());
    		driver = new ChromeDriver();
    	}
    	
    	if (DEFAULT_BROWSER_TYPE == WebDriverType.FF){
    		driver = new FirefoxDriver();
    	}
    	
    	if (DEFAULT_BROWSER_TYPE == WebDriverType.IE){
    		driver = new InternetExplorerDriver();
    	}
    	
    	driver.manage().window().maximize();
        return driver;
    }

	private static String getCromeWEbDriverFileNameForOS() {
		if(isWindows()){
			return "chromedriver-win.exe";
		}
		if(isMac()){
			return "chromedriver-mac";
		}
		if(isLinux32()){
			return "chromedriver-linux32";
		}
		if(isLinux64()){
			return "chromedriver-linux64";
		}
		
		throw new RuntimeException("지원하는 OS 가 없습니다.");
	}

	private enum WebDriverType {
		IE, FF, CHROME;
	}
	
	public static boolean isWindows() {
		 
		return (OS.indexOf("win") >= 0);
 
	}
 
	public static boolean isMac() {
 
		return (OS.indexOf("mac") >= 0);
 
	}
 
	public static boolean isLinux32() {
 
		throw new UnsupportedOperationException("리눅스는 아직 누가 구문을 채워주세용");
 
	}
 
	public static boolean isLinux64() {
		 
		throw new UnsupportedOperationException("리눅스는 아직 누가 구문을 채워주세용");
 
	}
	
}
