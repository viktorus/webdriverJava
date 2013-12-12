package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Page;

public class LoginPage extends Page{

	@FindBy(partialLinkText="Sign") private WebElement loginLnk;
	@FindBy(partialLinkText="Sign") private WebElement userFld;
	@FindBy(partialLinkText="Log") private WebElement pwdFld;
	@FindBy(partialLinkText="Log") private WebElement loginBtn;
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public HomePage doLogin(String SiteURL, String Username, String Password) {
		driver.get(SiteURL);
		loginLnk.click();
		userFld.sendKeys(Username);
		pwdFld.sendKeys(Password);
		loginBtn.click();
		return new HomePage(); 
	}
	
}
