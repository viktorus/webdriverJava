package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.Page;

public class HomePage extends Page {

	@FindBy(linkText="Weather") private WebElement weatherLnk;
	@FindBy(id="idcta-username") private WebElement signInLnk;
	
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
		
	/**
	 * Open Weather Page
	 * @return new WeatherPage
	 */
	public LoginPage gotoLoginPage() {
		signInLnk.click();
		Assert.assertEquals(driver.getTitle(), "BBC - Sign in", "Login page failed to open");
		return new LoginPage(); 
	}
	
	/**
	 * Open Weather Page
	 * @return new WeatherPage
	 */
	public WeatherPage gotoWeatherPage() {
		weatherLnk.click();
		Assert.assertEquals(driver.getTitle(), "BBC Weather", "Weather page failed to open");
		return new WeatherPage(); 
	}

	
}
