package com.epam.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class WebDriverTest {
	protected WebDriver webDriver;

	@BeforeMethod
	protected void setUp() {
		webDriver = new ChromeDriver();
	}

	@AfterMethod
	protected void tearDown() {
		webDriver.quit();
		webDriver = null;
	}
}
