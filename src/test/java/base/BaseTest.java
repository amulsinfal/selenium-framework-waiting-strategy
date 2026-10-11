package base;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utility.ConfigReader;

public class BaseTest {
	protected WebDriver driver;

	@BeforeMethod
	public void setup() {
		String browser = ConfigReader.getValue("browser");
		String url = ConfigReader.getValue("url");
		switch (browser.toLowerCase().trim()) {
		case "chrome":
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--incognito");
			driver = new ChromeDriver(chromeOptions);
			break;
		case "firefox":
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addArguments("--private");
			driver = new FirefoxDriver(firefoxOptions);
			break;
		case "edge":
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.addArguments("--inprivate");
			driver = new EdgeDriver(edgeOptions);
			break;
		default:
			System.out.println("Invalid browser name.");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(ConfigReader.getValue("implicitWait"))));
		driver.get(url);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}