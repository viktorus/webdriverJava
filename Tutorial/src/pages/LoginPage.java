package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Page;

public class LoginPage extends Page{

	@FindBy(partialLinkText="bbcid_unique") private WebElement userNameFld;
	@FindBy(partialLinkText="bbcid_password") private WebElement passwdFld;
	@FindBy(partialLinkText="bbcid_submit_button") private WebElement submitBtn;
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public HomePage doLogin(String usernm, String pwd) {
		userNameFld.sendKeys(usernm);
		passwdFld.sendKeys(pwd);
		submitBtn.click();
		return new HomePage(); 
	}
	
}