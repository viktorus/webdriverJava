package base;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;

public class Page {
	public static WebDriver driver = null;
	DesiredCapabilities capability = null;
	
	/**
	 * Creates webdriver instance with the following parameters
	 * 
	 * @param hubURL - url of the remote PC
	 * @param browserName - supported browsers firefox, chrome, iexplore
	 * @param type
	 * 			- remote - run the test on the remote machine using hubURL
	 * 			- local - runs the test locally
	 * 			- profile - starting specific Firefox profile - "wd" profile must be created before running in this mode
	 *
	 */
	public Page createDriver(String type, String browserName, String hubURL) {

		if (type.equalsIgnoreCase("local")) {
			if (browserName.equalsIgnoreCase("firefox")) {
				driver = new FirefoxDriver();
			} else if (browserName.equalsIgnoreCase("iexplore")) {
				driver = new InternetExplorerDriver();
			} else if (browserName.equalsIgnoreCase("chrome")) {
				driver = new ChromeDriver();
			}

		} else if (type.equalsIgnoreCase("profile")) {
			ProfilesIni allProfiles = new ProfilesIni();
			FirefoxProfile profile = allProfiles.getProfile("wd");
			driver = new FirefoxDriver(profile);

		} else if (type.equalsIgnoreCase("remote")) {

			if (browserName.equalsIgnoreCase("iexplore")) {
				capability = DesiredCapabilities.internetExplorer();
				capability.setCapability(CapabilityType.PLATFORM,
						Platform.WINDOWS);
			} else {
				capability = new DesiredCapabilities();
				capability.setCapability(CapabilityType.BROWSER_NAME,
						browserName);
				capability.setCapability(CapabilityType.PLATFORM,
						Platform.WINDOWS);
			}

			capability.setCapability(
					CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION,
					"ensureCleanSession");

			try {
				driver = new RemoteWebDriver(new URL(hubURL), capability);
				log("Driver started.");
			} catch (Exception e) {
				log("Driver failed to start");
			}
		}

		// set default timeout
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// maximize browser window
		driver.manage().window().maximize();
		
		return this;
	}
	
	/**
	 * Open site URL - specified in testng.xml
	 */
	public Page openURL(String siteURL) {
		driver.get(siteURL);
		return this;
	}

	/**
	 * Close current instance of webdriver
	 */
	public void closeDriver() {
		driver.close();
		driver.quit();
		log("Driver closed.");
	}
	
	/**
	 * Asserts if the page title is not Error
	 */
	public void checkIfNoError() {
		Assert.assertEquals(driver.getTitle() != "Error", true,
				"Error occurs while opening the page");
	}
	
	/**
	 * Prints a message (msg) to both console (eclipse) and reporter (ant)
	 * @param msg 
	 */
	public void log(String msg) {
		System.out.println(msg);
		Reporter.log(msg);
	}


}
