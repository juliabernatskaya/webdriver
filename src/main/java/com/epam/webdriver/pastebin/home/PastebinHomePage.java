package com.epam.webdriver.pastebin.home;

import com.epam.webdriver.common.PageObject;
import com.epam.webdriver.pastebin.home.select.option.Expiration;
import com.epam.webdriver.pastebin.home.select.option.Syntax;
import com.epam.webdriver.pastebin.paste.PastebinPastePage;
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
	@FindBy(id = "select2-postform-format-container")
	private WebElement syntaxSelect;
	@FindBy(xpath = "//div[@class='post-form__bottom']/descendant::button[@type='submit']")
	private WebElement submitButton;

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

	public PastebinHomePage selectSyntax(Syntax syntax) {
		selectOption(syntaxSelect, syntax.by());
		return this;
	}

	public PastebinPastePage submitPaste() {
		clickElement(submitButton);
		return new PastebinPastePage(webDriver);
	}

	@Override
	protected void init(WebDriver webDriver) {
		PageFactory.initElements(webDriver, this);
	}
}