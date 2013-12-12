package testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;
import base.Page;

public class TestCase1 extends Page {
	
	@BeforeClass
	@Parameters({"TYPE", "BROWSER", "HUB", "SITEURL"})
	public void beforeClass(String TYPE, String BROWSER, String HUB, String SITEURL) {
		createDriver(TYPE, BROWSER, HUB).openURL(SITEURL);
	}
	
	@Test(description="Search for current weather in Prague")
	public void getTodaysWeather() {
		new HomePage().gotoWeatherPage().searchLocation("Prague").logTodayWeather();
	}
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeDriver();
	}

}
