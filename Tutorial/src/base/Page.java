package base;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Page {
public static WebDriver driver = null;
public static Properties OR =null;
	

	public void createDriver() {
		if (driver == null) {
			ProfilesIni allProfiles = new ProfilesIni();
			FirefoxProfile profile = allProfiles.getProfile("wd");
			driver=new FirefoxDriver(profile);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
