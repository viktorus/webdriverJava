package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.LandingPage;
import base.Page;

public class Weather extends Page {
	
	@BeforeClass
	public void beforeClass() {
		createDriverFF();
		driver.get("http://jobvacancies.businesslink.gov.uk");
		
	}
	
	@Test
	public void Ahoj() {
		new LandingPage().doLogin();
	}

}
