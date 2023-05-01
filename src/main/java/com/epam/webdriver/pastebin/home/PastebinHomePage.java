package com.epam.webdriver.pastebin.home;

import com.epam.webdriver.common.PageObject;
import com.epam.webdriver.pastebin.home.select.option.Expiration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PastebinHomePage extends PageObject {

	@FindBy(id = "postform-text")
	private WebElement pasteInput;
	@FindBy(id = "postform-name")
	private WebElement titleInput;
	@FindBy(id = "select2-postform-expiration-container")
	private WebElement expirationSelect;

	public PastebinHomePage(WebDriver webDriver) {
		super(webDriver);
	}

	public PastebinHomePage enterPaste(String paste) {
		fillForm(pasteInput, paste);
		return this;
	}

	public PastebinHomePage enterTitle(String title) {
		fillForm(titleInput, title);
		return this;
	}

	public PastebinHomePage selectExpiration(Expiration expiration) {
		selectOption(expirationSelect, expiration.by());
		return this;
	}

	@Override
	protected void init(WebDriver webDriver) {
		PageFactory.initElements(webDriver, this);
	}
}