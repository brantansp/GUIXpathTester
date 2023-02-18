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
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import gui.GUIWindow;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	WebDriver driver;
	static Properties prop;
	static String downloadFilepath = System.getProperty("user.dir") + File.separator + "download";
	
	static {
		File dir = new File(".\\download");
		if (!dir.exists()) {
			dir.mkdirs();
		}

		File tmpDir = new File(System.getProperty("user.dir") + "\\configuration.ini");
		boolean exists = tmpDir.exists();
		if (!exists) {
			Writer writer = null;
			try {
				writer = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(System.getProperty("user.dir") + "\\configuration.ini"), "utf-8"));
				String driverPath = System.getProperty("user.dir");
				driverPath = driverPath.replace("\\", "\\\\");
				writer.write("chromedriver=" + driverPath + "\\\\driver\\\\chromedriver.exe");
			} catch (IOException ex) {

			} finally {
				try {
					writer.close();
				} catch (Exception ex) {

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

	public Properties getProp() {
		return prop;
	}

	public void openChrome() {
		WebDriverManager.chromedriver().setup();
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
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		options.setExperimentalOption("useAutomationExtension", false);
		options.setProxy(null);
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);
		chromePrefs.put("download.prompt_for_download", false);
		chromePrefs.put("download.directory_upgrade", true);
		chromePrefs.put("plugins.plugins_disabled", "Chrome PDF Viewer");
		chromePrefs.put("plugins.always_open_pdf_externally", true);
		chromePrefs.put("pdfjs.disabled", true);
		chromePrefs.put("profile.default_content_setting_values.notifications", 2);
		
		options.setExperimentalOption("prefs", chromePrefs);
		//System.setProperty("webdriver.chrome.driver", prop.getProperty("chromedriver"));		
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
	}

	public void openFirefox() {
		WebDriverManager.firefoxdriver().setup();
		FirefoxOptions options = new FirefoxOptions();
		options.setCapability("marionette", false);
		System.setProperty("webdriver.gecko.driver", prop.getProperty("firefoxdriver"));
		driver = new FirefoxDriver(options);
		driver.manage().window().maximize();
		
		driver.close();
		driver.get("");
		driver.getCurrentUrl();
		driver.getPageSource();
		driver.getTitle();
		driver.getWindowHandle();
		driver.getWindowHandles();
		driver.quit();
		driver.switchTo();
		
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();
		driver.navigate().to("");
		
		driver.manage().window().fullscreen();
		driver.manage().window().getPosition();
		driver.manage().window().getSize();
		driver.manage().window().maximize();
		
		
	}

	public void closeChrome() throws IOException {
		if (driver != null) {
			driver.close();
			System.out.println("Driver was closed");
		}
		Runtime.getRuntime().exec("taskkill /f /im chromedriver* /T");
		Runtime.getRuntime().exec("taskkill /f /im geckodriver* /T");
	}

	public void openChromeConsole() throws AWTException {
		String openDevTools = Keys.chord(Keys.CONTROL, Keys.SHIFT, "i");
		driver.findElement(By.xpath("//body")).sendKeys(openDevTools);
		String parentHandle = driver.getWindowHandle();
		driver.switchTo().window(parentHandle);
	}

	public void navigateToUrl(String url, String http) {
		if (!(url.contains("http://") || (url.contains("https://")))) {
			url = http + "://" + url;
		}

		System.out.println("Navigated to URL : " + url);
		driver.navigate().to(url);
	}

	public void clear(String xpath) {
		try {
			driver.findElement(By.xpath(xpath)).clear();
			System.out.println("The field was cleared for : " + xpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void click(String xpath) {
		try {
			driver.findElement(By.xpath(xpath)).click();
			System.out.println("The element was Clicked for : " + xpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void clickAll(String xpath) {
		try {
			List <WebElement> wb =driver.findElements(By.xpath(xpath));
			for (WebElement w : wb) {
				w.click();
			}
			System.out.println("The element was Clicked for : " + xpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendKeys(String xpath, String text) {
		try {
			driver.findElement(By.xpath(xpath)).sendKeys(text);
			System.out.println("Text : " + text + " send to the element : " + xpath);
		} catch (Exception e) {
			System.out.println("Text : " + text + " send to the element : " + xpath);
			e.printStackTrace();
		}
	}

	public void getText(String xpath) {
		try {
			System.out.println(
					"Text for the element : " + xpath + " is " + driver.findElement(By.xpath(xpath)).getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void isEnabled(String xpath) {
		if (driver.findElement(By.xpath(xpath)).isEnabled()) {
			System.out.println("The element : " + xpath + " is enabled");
		}
	}

	public void isSelected(String xpath) {
		if (driver.findElement(By.xpath(xpath)).isSelected()) {
			System.out.println("The element : " + xpath + " is selected");
		}
	}

	public void isDisplayed(String xpath) {
		if (driver.findElement(By.xpath(xpath)).isDisplayed()) {
			System.out.println("The element : " + xpath + " is displayed");
		}
	}
	
	public void submit(String xpath) {
		try {
			driver.findElement(By.xpath(xpath)).submit();
			System.out.println("Submitted to the element : " + xpath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
