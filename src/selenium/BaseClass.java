package selenium;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseClass {

	WebDriver driver;

	public void openChrome() {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--proxy-server='direct://'");
		options.addArguments("--proxy-bypass-list=*");
		options.addArguments("disable-infobars");
		options.addArguments("disable-extensions");
		options.addArguments("--start-maximized");
		options.addArguments("test-type");
		options.addArguments("--js-flags=--expose-gc");
		options.addArguments("--enable-precise-memory-info");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--disable-default-apps");
		options.addArguments("--window-size=1920,1080");
		options.addArguments("test-type=browser");
		options.addArguments("disable-infobars");
		options.setExperimentalOption("useAutomationExtension", false);
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
	}

	public static void main(String[] args) throws AWTException, InterruptedException {
		BaseClass obj = new BaseClass();
		obj.openChrome();
	}

	public void closeChrome() throws IOException {
		if (driver != null) {
			driver.close();
			System.out.println("Driver was closed");
		}
		Runtime.getRuntime().exec("taskkill /f /im chromedriver* /T");
	}

	public void openChromeConsole() throws AWTException {
		String openDevTools = Keys.chord(Keys.CONTROL, Keys.SHIFT, "i");
		driver.findElement(By.xpath("//body")).sendKeys(openDevTools);
		String parentHandle = driver.getWindowHandle();
		driver.switchTo().window(parentHandle);
	}

	public void navigateToUrl(String url) {
		if (!(url.contains("http://") || (url.contains("https://")))) {
			url = "http://" + url;
		}

		if (!(url.contains(".com"))) {
			url = url + ".com";
		}
		System.out.println("Navigated to URL : " + url);
		driver.navigate().to(url);
	}

}
