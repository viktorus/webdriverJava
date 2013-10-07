package base;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.opera.core.systems.OperaDriver;

public class Page {
public static WebDriver driver = null;
public static Properties OR =null;
	

	public void createDriverFF() {
		if (driver == null) {
			ProfilesIni allProfiles = new ProfilesIni();
			FirefoxProfile profile = allProfiles.getProfile("wd");
			driver=new FirefoxDriver(profile);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
	}
	
	/**
	 * Creates webdriver instance with the following parameters
	 * 
	 * @param hubURL url of the remote PC
	 * @param browserName name of the browser
	 * @param type remote OR local
	 * @throws MalformedURLException 
	 * @throws Exception
	 */
	public static void createDriver(String type, String browserName, String hubURL) throws MalformedURLException {

		DesiredCapabilities capability = null;
		
		if (type.equalsIgnoreCase("local")) {
	
			if (browserName.equalsIgnoreCase("firefox")) {
				driver = new FirefoxDriver();
			} else if (browserName.equalsIgnoreCase("iexplore")) {
				driver = new InternetExplorerDriver();
			} else if (browserName.equalsIgnoreCase("chrome")) {
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("opera")) {
				driver = new OperaDriver();
			}

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

				// Opera specific capabililty setting
				if (browserName.equals("opera")) {
					capability.setCapability("opera.binary",
							"c:/Program Files/Opera/opera.exe");
					capability.setCapability("opera.log.level", "CONFIG");
				}
			}
			capability.setCapability(
					CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION,
					"ensureCleanSession");

			System.out.println("Creating new Remote WebDriver instance");

			if (browserName.equals("opera")) {
				driver = new OperaDriver(capability);
			} else {
				driver = new RemoteWebDriver(new URL(hubURL), capability);
			}

		}
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// maximazing browser's window
		if (!browserName.equalsIgnoreCase("opera")) {
			driver.manage().window().maximize();
		}

	}
	
	/**
	 * 
	 * @param by1
	 *            = ID of the frame where the element is
	 * @param by2
	 *            = ID of the element to access - usually tagName
	 * @param i
	 *            = position of the element - 0=first, 1=second etc
	 */
	public static void selectList(By by1, By by2, int i) {

		WebElement menu = driver.findElement(by1);
		List<WebElement> menuItems = menu.findElements(by2);
		//System.out.println(menuItems.size());
		menuItems.get(i).click();

	}
	
	public static void waitOn(final By byType) {
		int timeout = 50;
		Wait<WebDriver> wait1;
		wait1 = new WebDriverWait(driver, timeout);
		wait1.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver webDriver) {
				return webDriver.findElement(byType) != null;
			}
		});

	}
	
	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
		} catch (Exception e) {
			return false;
		}

		return true;
	}
	
	public static void checkIfNoError() {
		Assert.assertEquals(driver.getTitle() != "Error", true, "Error occurs while opening the page");
	}

}
