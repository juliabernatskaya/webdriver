package com.epam.webdriver.pastebin.paste;

import com.epam.webdriver.common.PageObject;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PastebinPastePage extends PageObject {

	@FindBy(xpath = "//div[@class='info-top']/descendant::h1")
	private WebElement titleTag;
	@FindBy(xpath = "//div[@class='top-buttons']/descendant::a[contains(@href, '/archive')]")
	private WebElement syntaxTag;
	@FindBy(xpath = "//div[contains(@class, 'source')]/ol/li")
	private List<WebElement> pasteRows;

	public PastebinPastePage(WebDriver webDriver) {
		super(webDriver);
	}

	public String getTitle() {
		return getElementText(titleTag);
	}

	public String getSyntax() {
		return getElementText(syntaxTag);
	}

	public String getPaste() {
		return getElementsText(pasteRows);
	}

	@Override
	protected void init(WebDriver webDriver) {
		PageFactory.initElements(webDriver, this);
	}
}