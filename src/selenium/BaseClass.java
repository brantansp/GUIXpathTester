package selenium;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import gui.GUIWindow;

public class BaseClass {

	WebDriver driver;
	static Properties prop;

	static {
		File dir = new File(".\\driver");
    	if (!dir.exists())
    	{
    		dir.mkdirs();
    	}
    	
		File tmpDir = new File(System.getProperty("user.dir")+"\\configuration.ini");
		boolean exists = tmpDir.exists();
		if (!exists) {
			Writer writer = null;
			try {
			    writer = new BufferedWriter(new OutputStreamWriter(
			          new FileOutputStream(System.getProperty("user.dir")+"\\configuration.ini"), "utf-8"));
			    String driverPath = System.getProperty("user.dir");
			    driverPath = driverPath.replace("\\", "\\\\");
			    writer.write("chromedriver="+driverPath+"\\\\driver\\\\chromedriver.exe");
			} catch (IOException ex) {

			} finally {
			   try {writer.close();} catch (Exception ex) {
				   
			   }
			}
		}
		prop = new Properties();
		try (FileInputStream configuration = new FileInputStream(
				System.getProperty("user.dir") + "/configuration.ini");) {
			prop.load(configuration);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Properties getProp () {
		return prop;
	}
	
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
		System.setProperty("webdriver.chrome.driver", prop.getProperty("chromedriver"));
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
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
