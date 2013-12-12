package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Page;

public class LoginPage extends Page{

	@FindBy(partialLinkText="") private WebElement test;
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public HomePage doLogin() {
		//TODO: add logic
		return new HomePage(); 
	}
	
}
