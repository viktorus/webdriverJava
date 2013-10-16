package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Page;

public class WeatherPage extends Page {
		
	@FindBy(css="#locator-form-search") private WebElement weatherLnk;
	@FindBy(css="#locator-form-submit") private WebElement submitBtn;
	@FindBy(css=".units-value.temperature-value.temperature-value-unit-c") private WebElement todaysWeatherTxt;
	
	
	public WeatherPage() {
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * Enter location to the search field and submit
	 * @param location
	 * @return
	 */
	public WeatherPage searchLocation(String location) {
		weatherLnk.sendKeys(location);
		submitBtn.click();
		return this; 
	}
	
	/**
	 * Log todays weather for specified location
	 * @return
	 */
	public WeatherPage logTodayWeather() {
		log("Todays weather is - "+todaysWeatherTxt.getText());
		return this; 
	}
	
}
