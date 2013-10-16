package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.LoginPage;
import base.Page;

public class TestCase1 extends Page {
	
	@BeforeClass
	@Parameters({"TYPE", "BROWSER", "HUB", "SITEURL", "USERNAME", "PWD"})
	public void beforeClass(String TYPE, String BROWSER, String HUB, String SITEURL, String USERNAME, String PWD) throws Exception  {
		createDriver(TYPE, BROWSER, HUB);
		new LoginPage().doLogin(SITEURL, USERNAME, PWD);
	}
	
	@Test
	public void Ahoj() {
		
	}

}
