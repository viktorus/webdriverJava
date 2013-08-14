package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Page;

public class LandingPage extends Page{

	@FindBy(partialLinkText="Sign") private WebElement loginLink;
	@FindBy(partialLinkText="Log") private WebElement loginLink2;
	
	public LandingPage() {
		PageFactory.initElements(driver, this);
	}
	
	public LandingPage doLogin() {
		if (loginLink.getText().contains("Sign") || loginLink2.getText().contains("Log")) {
			loginLink.click();
		}
		return this; 
		
	}
	
	public WeatherPage gotoWeatherPage() {
		return new WeatherPage();
		

	}
	
	
}
