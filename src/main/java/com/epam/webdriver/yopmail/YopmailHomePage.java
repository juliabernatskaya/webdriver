package com.epam.webdriver.yopmail;

import com.epam.webdriver.common.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@SuppressWarnings("unused")
public class YopmailHomePage extends PageObject {

	@FindBy(xpath = "//div[@id='listeliens']/a[@href='email-generator']")
	private WebElement randomEmailLink;

	public YopmailHomePage(WebDriver webDriver) {
		super(webDriver);
	}

	public YopmailRandomGeneratorPage tapRandomEmailLink() {
		clickElement(randomEmailLink);
		return new YopmailRandomGeneratorPage(webDriver);
	}

	@Override
	protected void init(WebDriver webDriver) {
		PageFactory.initElements(webDriver, this);
	}
}
