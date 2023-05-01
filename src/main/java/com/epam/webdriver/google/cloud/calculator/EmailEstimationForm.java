package com.epam.webdriver.google.cloud.calculator;

import com.epam.webdriver.common.Locatable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EmailEstimationForm extends GoogleCloudCalculatorFrame implements Locatable {

	private static final By LOCATOR = By.xpath("//form[@name='emailForm']");

	@FindBy(xpath = "//input[@type='email']")
	private WebElement emailInput;
	@FindBy(xpath = "//button[contains(text(), 'Send Email')]")
	private WebElement sendEmailButton;

	protected EmailEstimationForm(WebDriver webDriver) {
		super(webDriver);
	}

	public EmailEstimationForm enterEmailAddress(String email) {
		fillForm(emailInput, email);
		return this;
	}

	public void sendEmail() {
		clickElement(sendEmailButton);
	}

	@Override
	public By getLocator() {
		return LOCATOR;
	}

	@Override
	protected void init(WebDriver webDriver) {
		PageFactory.initElements(webDriver, this);
	}
}
