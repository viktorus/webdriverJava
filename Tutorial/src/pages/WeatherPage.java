package pages;

import org.openqa.selenium.By;
import base.Page;

public class WeatherPage extends Page{


	public LandingPage doLogin(String SiteURL, String Username, String Password) {
		
		driver.get(SiteURL);
		driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_cphBody_btnLogin")).click();
		driver.findElement(By.id("ctl00_fixedContent_Username_uid_UIDSingleField")).sendKeys(Username);
		driver.findElement(By.id("ctl00_fixedContent_Username_pw")).sendKeys(Password);
		driver.findElement(By.id("ctl00_fixedContent_Username_loginButton")).click();
		System.out.println("doLogin completed");
		
		return new LandingPage();
	
	}
	
	public void downloadPDF() {
		
	}
	
	public void register() {
		
	}
	
	public void forgotUserID() {
		driver.findElement(By.id("ctl00_fixedContent_Username_loginButton")).click();
	}
	
	public void forgotPWD() {
		driver.findElement(By.id("ctl00_fixedContent_Username_loginButton")).click();
	}
}
