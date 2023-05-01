package com.epam.webdriver.google.cloud.search.result;

import com.epam.webdriver.common.PageObject;
import com.epam.webdriver.google.cloud.calculator.GoogleCloudCalculatorPage;
import java.util.List;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GoogleCloudSearchResultsPage extends PageObject {

	private static final String PRICING_CALCULATOR_LINK_TEXT = "Google Cloud Platform Pricing Calculator";

	@FindBy(xpath = "//div[@class='gsc-webResult gsc-result']/descendant::div[@class='gs-title']/a")
	private List<WebElement> searchResults;

	public GoogleCloudSearchResultsPage(WebDriver webDriver) {
		super(webDriver);
	}

	public GoogleCloudCalculatorPage tapGoogleCloudPlatformPricingCalculatorLink() {
		wait.until(ExpectedConditions.visibilityOfAllElements(searchResults))
			.stream()
			.filter(element -> PRICING_CALCULATOR_LINK_TEXT.equals(element.getText()))
			.findFirst().orElseThrow(
				() -> new NoSuchElementException("link with text '" + PRICING_CALCULATOR_LINK_TEXT + "' not found"))
			.click();
		return new GoogleCloudCalculatorPage(webDriver);
	}

	@Override
	protected void init(WebDriver webDriver) {
		PageFactory.initElements(webDriver, this);
	}
}
