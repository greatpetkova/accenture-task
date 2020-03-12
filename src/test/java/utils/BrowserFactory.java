package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserFactory {
	public static WebDriver getWebDriver(String browser) {
		WebDriver driver;

		switch (browser) {
			case "firefox":
				if (System.getenv("RUN_ENV").equals("DOCKER")) {
					FirefoxOptions options = new FirefoxOptions();

					options.setHeadless(true);
					options.addArguments("--disable-dev-shm-usage"); // fixing linux limited resource problems

					driver = new FirefoxDriver(options);
				} else {
					driver = new FirefoxDriver();
				}
				break;
			case "chrome":
			default:
				if (EnvirontmentHelper.isDockerEnv()) {
					ChromeOptions options = new ChromeOptions();

					options.setHeadless(true);
					options.addArguments("--disable-dev-shm-usage"); // fixing linux limited resource problems

					driver = new ChromeDriver(options);
				} else {
					driver = new ChromeDriver();
				}
				break;
		}

		return driver;
	}

	public static WebDriver getChromeDriver() {
		return getWebDriver("chrome");
	}
}
 