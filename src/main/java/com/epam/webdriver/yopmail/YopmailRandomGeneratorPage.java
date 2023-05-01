package com.epam.webdriver.yopmail;

import com.epam.webdriver.common.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YopmailRandomGeneratorPage extends PageObject {

	protected YopmailRandomGeneratorPage(WebDriver webDriver) {
		super(webDriver);
	}

	@FindBy(id = "cprnd")
	private WebElement copyToClipboardButton;

	@FindBy(xpath = "//button[@onclick='egengo();']")
	private WebElement checkInboxButton;

	public YopmailRandomGeneratorPage tapCopyToClipboardButton() {
		clickElement(copyToClipboardButton);
		return this;
	}

	public YopmailMailboxPage tapCheckInboxButton() {
		clickElement(checkInboxButton);
		return new YopmailMailboxPage(webDriver);
	}

	@Override
	protected void init(WebDriver webDriver) {
		PageFactory.initElements(webDriver, this);
	}
}
