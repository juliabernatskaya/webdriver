package com.epam.webdriver.google.cloud.home;

import com.epam.webdriver.common.PageObject;
import com.epam.webdriver.google.cloud.search.result.GoogleCloudSearchResultsPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleCloudPage extends PageObject {

	@FindBy(xpath = "//input[@aria-label = 'Search']")
	private WebElement searchInput;

	public GoogleCloudPage(WebDriver webDriver) {
		super(webDriver);
	}

	public GoogleCloudSearchResultsPage search(String searchQuery) {
		fillForm(searchInput, searchQuery + Keys.ENTER);
		return new GoogleCloudSearchResultsPage(webDriver);
	}

	@Override
	protected void init(WebDriver webDriver) {
		PageFactory.initElements(webDriver, this);
	}
}
